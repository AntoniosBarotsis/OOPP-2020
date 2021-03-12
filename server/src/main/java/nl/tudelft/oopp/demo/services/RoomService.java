package nl.tudelft.oopp.demo.services;

import java.util.List;
import java.util.Set;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
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
     * @return the private password
     */
    public String getPrivatePassword(long roomId) {
        // Later perform some sort of check here to see if the user has permission
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
     * Find all polls set.
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
     * @return boolean
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
     */
    public void banUser(long roomId, String ip, String elevatedPassword) {
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
     */
    public void unbanUser(long roomId, String ip, String elevatedPassword) {
        Room room = roomRepository.getOne(roomId);

        if (!elevatedPassword.equals(room.getElevatedPassword())) {
            return;
        }

        roomRepository.unbanUser(roomId, ip);
    }
}
