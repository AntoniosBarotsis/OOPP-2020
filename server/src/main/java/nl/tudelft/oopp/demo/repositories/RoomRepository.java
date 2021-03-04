package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.Question;
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
    @Query(value = "SELECT normal_password FROM rooms WHERE id = ?1", nativeQuery = true)
    String getPublicPassword(long roomId);

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    @Transactional
    @Query(value = "SELECT elevated_password FROM rooms WHERE id = ?1", nativeQuery = true)
    String getPrivatePassword(long roomId);

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    @Transactional
    @Query(value = "SELECT q FROM Question q, Room r where r.id = ?1 AND q.id = r.id")
    Set<Question> findAllQuestions(long roomId);

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooFast = 1 WHERE r.id =?1")
    void incrementTooFast(long roomId);

    /**
     * Decrement too fast.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooFast = -1 WHERE r.id =?1")
    void decrementTooFast(long roomId);

    /**
     * Increment too slow.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooSlow = 1 WHERE r.id =?1")
    void incrementTooSlow(long roomId);

    /**
     * Decrement too slow.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Room r SET r.tooSlow = -1 WHERE r.id =?1")
    void decrementTooSlow(long roomId);
}
