package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;

import nl.tudelft.oopp.demo.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Poll repository.
 */
@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {

    @Modifying
    @Transactional
    @Query(value = "UPDATE Poll p SET p.status = ?2 WHERE p.id = ?1")
    void updateStatus(long pollId, Poll.PollStatus status);

    @Transactional
    @Query(value = "SELECT p.status FROM Poll p WHERE p.id = ?1")
    String getStatus(long pollId);
}
