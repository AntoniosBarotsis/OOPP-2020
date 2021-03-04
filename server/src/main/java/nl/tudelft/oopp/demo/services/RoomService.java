package nl.tudelft.oopp.demo.services;

import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.Question;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The type Room service.
 */
@Service
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    /**
     * Instantiates a new Room service.
     *
     * @param roomRepository the room repository
     */
    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    public Room getOne(long id) {
        return roomRepository.getOne(id);
    }

    /**
     * Gets public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    public String getPublicPassword(long roomId) {
        return roomRepository.getPublicPassword(roomId);
    }

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    public String getPrivatePassword(long roomId) {
        // Later perform some sort of check here to see if the user has permission
        return roomRepository.getPrivatePassword(roomId);
    }

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    public Set<Question> findAllQuestions(long roomId) {
        return roomRepository.findAllQuestions(roomId);
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
