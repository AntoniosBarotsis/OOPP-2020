package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Room repository.
 */
@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    /**
     * Gets public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @Transactional
    @Query(value = "SELECT r.normalPassword FROM Room r WHERE r.id = ?1")
    String getPublicPassword(long roomId);

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    @Transactional
    @Query(value = "SELECT r.elevatedPassword FROM Room r WHERE r.id = ?1")
    String getPrivatePassword(long roomId);

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    @Transactional
    @Query(value = "SELECT r.questions FROM Room r WHERE r.id = ?1")
    Set<Question> findAllQuestions(long roomId);

    /**
     * Find all polls set.
     *
     * @param roomId the room id
     * @return the set
     */
    @Transactional
    @Query(value = "SELECT r.polls FROM Room r WHERE r.id = ?1")
    Set<Poll> findAllPolls(long roomId);

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooFast = r.tooFast + 1 WHERE r.id = ?1")
    void incrementTooFast(long roomId);

    /**
     * Decrement too fast.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooFast = r.tooFast - 1 WHERE r.id = ?1")
    void decrementTooFast(long roomId);

    /**
     * Increment too slow.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooSlow = r.tooSlow + 1 WHERE r.id = ?1")
    void incrementTooSlow(long roomId);

    /**
     * Decrement too slow.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooSlow = r.tooSlow - 1 WHERE r.id = ?1")
    void decrementTooSlow(long roomId);
}
