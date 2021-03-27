package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Poll;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Poll repository.
 */
@Repository
public interface PollRepository extends JpaRepository<Poll, Long> {
}
