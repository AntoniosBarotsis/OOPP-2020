package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Answer repository.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

    @Transactional
    @Query(value = "SELECT COUNT(a.pollId) FROM Answer a WHERE a.pollId = ?1")
    int getNumAnswers(long pollId);
}
