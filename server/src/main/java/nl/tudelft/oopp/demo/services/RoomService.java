package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.RoomHelper;
import nl.tudelft.oopp.demo.entities.log.LogBan;
import nl.tudelft.oopp.demo.entities.log.LogCollection;
import nl.tudelft.oopp.demo.entities.log.LogJoin;
import nl.tudelft.oopp.demo.entities.log.LogQuestion;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidPasswordException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type Room service.
 */
@Service
@AllArgsConstructor
@Log4j2
public class RoomService {
    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final LogEntryRepository logEntryRepository;
    private final RoomConfigRepository roomConfigRepository;
    private final UserService userService;

    /**
     * Returns a list of all rooms.
     *
     * @return the list
     */
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    /**
     * Gets the specified room.
     *
     * @param id the id
     * @return the one
     */
    public Room getOne(long id) {
        return roomRepository.getOne(id);
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
     */
    public Set<Question> findAllQuestions(long roomId) {
        return roomRepository.findAllQuestions(roomId);
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
     * Increment normal speed.
     *
     * @param roomId the room id
     */
    public void incrementNormalSpeed(long roomId) {
        roomRepository.incrementNormalSpeed(roomId);
    }

    /**
     * Decrement normal speed.
     *
     * @param roomId the room id
     */
    public void decrementNormalSpeed(long roomId) {
        roomRepository.decrementNormalSpeed(roomId);
    }

    /**
     * Returns true if the user has been banned in the given room.
     *
     * @param roomId the room id
     * @param id     the id
     * @return boolean boolean
     */
    public boolean isBanned(long roomId, long id) {
        User userToBan = userRepository.getOne(id);
        return roomRepository.getOne(roomId).getBannedIps().contains(userToBan.getIp());
    }

    /**
     * Bans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param userId           the user id
     * @param idToBeBanned     the to be banned id
     * @param elevatedPassword the elevated password
     * @throws UnauthorizedException the unauthorized exception
     */
    public void banUser(long roomId, long userId, long idToBeBanned, String elevatedPassword)
            throws UnauthorizedException {
        if (isNotAuthorized(roomId, userId)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        Room room = roomRepository.getOne(roomId);

        if (!elevatedPassword.equals(room.getElevatedPassword())) {
            return;
        }

        User userToBan = userRepository.getOne(idToBeBanned);
        roomRepository.banUser(roomId, userToBan.getIp());

        User userThatBans = userRepository.getOne(userId);
        LogBan logBan = new LogBan(room, userThatBans, userToBan.getIp(), new Date());
        logEntryRepository.save(logBan);
    }

    /**
     * Unbans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param userId           the id
     * @param idToBeBanned     the to be banned id
     * @param elevatedPassword the elevated password
     * @throws UnauthorizedException    the unauthorized exception
     * @throws InvalidPasswordException the invalid password exception
     */
    public void unbanUser(long roomId, long userId, long idToBeBanned, String elevatedPassword)
            throws UnauthorizedException, InvalidPasswordException {
        if (isNotAuthorized(roomId, userId)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        Room room = roomRepository.getOne(roomId);

        if (!elevatedPassword.equals(room.getElevatedPassword())) {
            throw new InvalidPasswordException("The password '" + elevatedPassword
                    + " does not match the Room's Elevated password'");
        }

        User userToBan = userRepository.getOne(idToBeBanned);
        roomRepository.unbanUser(roomId, userToBan.getIp());
    }

    /**
     * Export log log collection.
     *
     * @param roomId the room id
     * @param ip     the ip
     * @return the log collection
     */
    public LogCollection exportLog(long roomId, String ip) {
        if (isNotAuthorized(roomId, ip)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        List<LogBan> bans = logEntryRepository.findAllBans(roomId);
        List<LogJoin> joins = logEntryRepository.findAllJoins(roomId);
        List<LogQuestion> questions = logEntryRepository.findAllQuestions(roomId);

        return new LogCollection(bans, joins, questions);
    }

    /**
     * Sets ongoing.
     *
     * @param roomId    the room id
     * @param isOngoing the is ongoing
     * @param userId    the user id
     */
    public void setOngoing(long roomId, boolean isOngoing, long userId) {
        if (isNotAuthorized(roomId, userId)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        if (roomRepository.getOne(roomId).getAdmin() != userId) {
            throw new UnauthorizedException("User not authorized (not the room admin)");
        }

        roomRepository.setOngoing(roomId, isOngoing);
    }


    /**
     * Sets config.
     *
     * @param roomId     the room id
     * @param roomConfig the room config
     * @param userId     the user id
     */
    public void setConfig(long roomId, RoomConfig roomConfig, long userId) {
        if (isNotAuthorized(roomId, userId)) {
            throw new UnauthorizedException("User not authorized (not an elevated user)");
        }

        int studentRefreshRate = roomConfig.getStudentRefreshRate();
        int modRefreshRate = roomConfig.getModRefreshRate();
        int questionCooldown = roomConfig.getQuestionCooldown();
        int paceCooldown = roomConfig.getPaceCooldown();

        roomConfigRepository.setConfig(roomId, studentRefreshRate, modRefreshRate,
                questionCooldown, paceCooldown);
    }

    /**
     * Returns true if the user's IP is not a moderator IP.
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

        return !authorizedIps.contains(ip);
    }

    /**
     * Returns true if the user's id is not a moderator id.
     *
     * @param roomId the room id
     * @param id     the id
     * @return the boolean
     */
    public boolean isNotAuthorized(long roomId, long id) {
        List<Long> authorizedIds = roomRepository
                .getOne(roomId)
                .getModerators()
                .stream()
                .map(User::getId)
                .collect(Collectors.toList());

        return !authorizedIds.contains(id);
    }

    //    /**
    //     * Schedule a new room.
    //     *
    //     * @param username the lecturer's username
    //     * @param ip       the lecturer's ip
    //     * @param title    the title of the room
    //     * @param date     the starting date/time for the room
    //     * @return the newly created room
    //     */
    //    public Room scheduleRoom(String username, String ip, String title, long date) {
    //        Room room = createRoom(username, ip, title);
    //        room.setStartingDate(new Date(date));
    //        return room;
    //    }

    /**
     * Create a new room.
     *
     * @param roomHelper the room helper
     * @param ip         the lecturer's ip
     * @return the newly created room
     */
    public Room createRoom(RoomHelper roomHelper, String ip) {
        ElevatedUser user = new ElevatedUser(roomHelper.getUsername(), ip, true);
        userRepository.save(user);

        if (roomHelper.getRoomConfig() == null) {
            roomHelper.setRoomConfig(new RoomConfig());
        }

        roomConfigRepository.save(roomHelper.getRoomConfig());

        Room room = roomHelper.createRoom(user);
        roomRepository.save(room);

        return room;
    }

    /**
     * Join a room.
     *
     * @param password the room's password
     * @param username the user's username
     * @param ip       the user's ip
     * @return the user
     */
    public User join(String password, String username, String ip) {
        Long userId = userRepository.getUser(username, ip);
        if (userId != null) {
            return userRepository.getOne(userId);
        }
        boolean isElevated = true;
        Long id = roomRepository.getElevatedRoomId(password);
        if (id == null) {
            id = roomRepository.getNormalRoomId(password);
            isElevated = false;
        }
        if (id == null) { // Incorrect password
            return null; // TODO Throw error
        }
        Room room = roomRepository.getOne(id);
        Date currentDate = new Date();
        if (!isElevated && currentDate.before(room.getStartingDate())) {
            // Joining before start date
            return null; // TODO Better error handling
        }
        User user;
        if (isElevated) {
            user = new ElevatedUser(username, ip);
        } else {
            user = new Student(username, ip);
        }
        userRepository.save(user);
        return user;
    }

    /**
     * Get a room.
     *
     * @param password the room's password
     * @return the room
     */
    public Room getRoom(String password) {
        Long id = roomRepository.getElevatedRoomId(password);
        if (id == null) {
            id = roomRepository.getNormalRoomId(password);
        }
        if (id == null) {
            return null; // TODO Throw error
        }
        return roomRepository.getOne(id);
    }
}
