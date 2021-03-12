package nl.tudelft.oopp.demo.services;

import java.util.Date;
import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.User;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Room service.
 */
@Service
public class RoomService {

    @Autowired
    private final RoomRepository roomRepository;

    @Autowired
    private final UserRepository userRepository;

    /**
     * Instantiates a new Room service.
     *
     * @param roomRepository the room repository
     */
    public RoomService(RoomRepository roomRepository, UserRepository userRepository) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
    }

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
     * @param id the room id
     * @return the room
     */
    public Room getOne(long id) {
        return roomRepository.getOne(id);
    }

    /**
     * Create a new room.
     *
     * @param userId the id of the admin of the room
     * @param title the title of the room
     * @return the newly created room
     */
    public Room createRoom(long userId, String title) {
        User user = userRepository.getOne(userId);
        Room room = new Room(title, user);
        roomRepository.save(room);
        return room;
    }

    /**
     * Schedule a new room.
     *
     * @param userId the id of the admin of the room
     * @param title the title of the room
     * @param date the starting date/time for the room
     * @return the newly created room
     */
    public Room scheduleRoom(long userId, String title, Date date) {
        Room room = createRoom(userId, title);
        room.setStartingDate(date);
        return room;
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
        // TODO Later perform some sort of check here to see if the user has permission
        return roomRepository.getPrivatePassword(roomId);
    }

    /**
     * Returns a set of all the  questions of a room.
     *
     * @param roomId the room id
     * @return the set of questions
     */
    public Set<Question> findAllQuestions(long roomId) {
        return roomRepository.findAllQuestions(roomId);
    }

    /**
     * Returns a set of all the polls of a room.
     *
     * @param roomId the room id
     * @return the set of polls
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
}
