package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.log.LogBan;
import nl.tudelft.oopp.demo.entities.log.LogEntry;
import nl.tudelft.oopp.demo.entities.log.LogJoin;
import nl.tudelft.oopp.demo.entities.log.LogQuestion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Log entry repository.
 */
@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
    /**
     * Find all bans.
     *
     * @param roomId the room id
     * @return the list
     */
    @Transactional
    @Query(value = "SELECT * FROM LOG_BAN WHERE room = ?1", nativeQuery = true)
    List<LogBan> findAllBans(long roomId);

    /**
     * Find all joins.
     *
     * @param roomId the room id
     * @return the list
     */
    @Transactional
    @Query(value = "SELECT * FROM LOG_JOIN WHERE room = ?1", nativeQuery = true)
    List<LogJoin> findAllJoins(long roomId);

    /**
     * Find all questions.
     *
     * @param roomId the room id
     * @return the list
     */
    @Transactional
    @Query(value = "SELECT * FROM LOG_QUESTION WHERE room = ?1", nativeQuery = true)
    List<LogQuestion> findAllQuestions(long roomId);
}
