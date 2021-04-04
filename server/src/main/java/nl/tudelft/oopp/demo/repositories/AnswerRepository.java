package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Answer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * The interface Answer repository.
 */
@Repository
public interface AnswerRepository extends JpaRepository<Answer, Long> {

}
