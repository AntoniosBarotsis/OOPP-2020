package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Room config repository.
 */
@Repository
public interface RoomConfigRepository extends JpaRepository<RoomConfig, Long> {

    /**
     * Sets the room config.
     *
     * @param roomId             the room id
     * @param studentRefreshRate the student refresh rate
     * @param modRefreshRate     the mod refresh rate
     * @param questionCooldown   the question cooldown
     * @param paceCooldown       the pace cooldown
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE ROOM_CONFIG c "
        + "SET c.STUDENT_REFRESH_RATE = ?2, "
        + "c.MOD_REFRESH_RATE = ?3, "
        + "c.QUESTION_COOLDOWN = ?4, "
        + "c.PACE_COOLDOWN = ?5 "
        + "WHERE EXISTS "
        + "(SELECT * FROM ROOMS r WHERE r.ROOM_CONFIG_ID = c.id AND r.id = ?1)",
        nativeQuery = true)
    void setConfig(long roomId, int studentRefreshRate, int modRefreshRate, int questionCooldown,
                   int paceCooldown);
}
