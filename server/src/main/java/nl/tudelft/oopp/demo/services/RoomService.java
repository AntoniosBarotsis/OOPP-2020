package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Collection;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.serializers.QuestionSerializer;
import nl.tudelft.oopp.demo.entities.serializers.RoomSerializer;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidPasswordException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.stereotype.Service;

/**
 * The type Room service.
 */
@Service
@AllArgsConstructor
public class RoomService {
    private final RoomRepository roomRepository;

    /**
     * Returns a list of all rooms.
     *
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    public String findAll() throws JsonProcessingException {
        return mapRoom(roomRepository.findAll());
    }

    /**
     * Gets the specified room.
     *
     * @param id the id
     * @return the one
     * @throws JsonProcessingException the json processing exception
     */
    public String getOne(long id) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Room.class, new RoomSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(roomRepository.getOne(id));
    }

    /**
     * Gets public password of a room.
     *
     * @param roomId the room id
     * @return the public password
     */
    public String getPublicPassword(long roomId) {
        return roomRepository.getPublicPassword(roomId);
    }

    /**
     * Gets private password of a room.
     *
     * @param roomId the room id
     * @param ip     the ip
     * @return the private password
     * @throws UnauthorizedException the unauthorized exception
     */
    public String getPrivatePassword(long roomId, String ip) throws UnauthorizedException {
        if (isNotAuthorized(roomId, ip)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        return roomRepository.getPrivatePassword(roomId);
    }

    /**
     * Returns a set of all the  questions of a room.
     *
     * @param roomId the room id
     * @return the set
     * @throws JsonProcessingException the json processing exception
     */
    public String findAllQuestions(long roomId) throws JsonProcessingException {
        return mapQuestion(roomRepository.findAllQuestions(roomId));
    }

    /**
     * Find all polls.
     *
     * @param roomId the room id
     * @return the set
     */
    public Set<Poll> findAllPolls(long roomId) {
        return roomRepository.findAllPolls(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    public void incrementTooFast(long roomId) {
        roomRepository.incrementTooFast(roomId);
    }

    /**
     * Decrement too fast.
     *
     * @param roomId the room id
     */
    public void decrementTooFast(long roomId) {
        roomRepository.decrementTooFast(roomId);
    }

    /**
     * Increment too slow.
     *
     * @param roomId the room id
     */
    public void incrementTooSlow(long roomId) {
        roomRepository.incrementTooSlow(roomId);
    }

    /**
     * Decrement too slow.
     *
     * @param roomId the room id
     */
    public void decrementTooSlow(long roomId) {
        roomRepository.decrementTooSlow(roomId);
    }

    /**
     * Returns true if the user has been banned in the given room.
     *
     * @param roomId the room id
     * @param ip     the ip
     * @return boolean boolean
     */
    public boolean isBanned(long roomId, String ip) {
        return roomRepository.getOne(roomId).getBannedIps().contains(ip);
    }

    /**
     * Bans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param ip               the ip
     * @param elevatedPassword the elevated password
     * @throws UnauthorizedException the unauthorized exception
     */
    public void banUser(long roomId, String ip, String elevatedPassword)
        throws UnauthorizedException {
        if (isNotAuthorized(roomId, ip)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        Room room = roomRepository.getOne(roomId);

        if (!elevatedPassword.equals(room.getElevatedPassword())) {
            return;
        }

        roomRepository.banUser(roomId, ip);
    }

    /**
     * Unbans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param ip               the ip
     * @param elevatedPassword the elevated password
     * @throws UnauthorizedException    the unauthorized exception
     * @throws InvalidPasswordException the invalid password exception
     */
    public void unbanUser(long roomId, String ip, String elevatedPassword)
        throws UnauthorizedException, InvalidPasswordException {
        if (isNotAuthorized(roomId, ip)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        Room room = roomRepository.getOne(roomId);

        if (!elevatedPassword.equals(room.getElevatedPassword())) {
            throw new InvalidPasswordException("The password '" + elevatedPassword
                + " does not match the Room's Elevated password'");
        }

        roomRepository.unbanUser(roomId, ip);
    }

    /**
     * Map question string.
     *
     * @param questions the questions
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String mapQuestion(Collection<Question> questions) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Question.class, new QuestionSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(questions);
    }

    /**
     * Map user string.
     *
     * @param rooms the users
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String mapRoom(Collection<Room> rooms) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Room.class, new RoomSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(rooms);
    }

    /**
     * Is authorized boolean.
     *
     * @param roomId the room id
     * @param ip     the ip
     * @return the boolean
     */
    public boolean isNotAuthorized(long roomId, String ip) {
        List<String> authorizedIps = roomRepository
            .getOne(roomId)
            .getModerators()
            .stream()
            .map(User::getIp)
            .collect(Collectors.toList());

        System.out.println(authorizedIps);
        return !authorizedIps.contains(ip);
    }
}
