package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
}
