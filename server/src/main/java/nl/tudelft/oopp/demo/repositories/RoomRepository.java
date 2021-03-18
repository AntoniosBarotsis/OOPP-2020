package nl.tudelft.oopp.demo.repositories;

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

    /**
     * Bans a user in the given room given the correct elevated password.
     *
     * @param roomId the room id
     * @param ip     the ip
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO room_banned_ips (room_id, banned_ips) VALUES (?1, ?2)",
        nativeQuery = true)
    void banUser(long roomId, String ip);

    /**
     * Unbans a user in the given room given the correct elevated password.
     *
     * @param roomId the room id
     * @param ip     the ip
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM room_banned_ips WHERE banned_ips = ?2 AND room_id = ?1",
        nativeQuery = true)
    void unbanUser(long roomId, String ip);

    /**
     * Get room id when given elevatedPassword.
     *
     * @param elevatedPassword the room's elevatedPassword
     * @return room id or null if the password is not a valid elevatedPassword
     */
    @Transactional
    @Query(value = "SELECT r.id FROM Room r WHERE r.elevatedPassword = ?1")
    Long getElevatedRoomId(String elevatedPassword);

    /**
     * Get room id when given normalPassword.
     *
     * @param normalPassword the room's normalPassword
     * @return room id or null if the password is not a valid normalPassword
     */
    @Transactional
    @Query(value = "SELECT r.id FROM Room r WHERE r.normalPassword = ?1")
    Long getNormalRoomId(String normalPassword);
}
