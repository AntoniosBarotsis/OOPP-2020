package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

/**
 * The interface Question repository.
 */
@Repository
public interface QuestionRepository extends JpaRepository<Question, Long> {
    /**
     * Add question.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO rooms_questions (room_id, questions_id) VALUES (?1, ?2)",
        nativeQuery = true)
    void addQuestion(long roomId, long questionId);

    /**
     * Delete one question.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rooms_questions WHERE rooms_questions.room_id = ?1 AND "
        + "rooms_questions.questions_id = ?2",
        nativeQuery = true)
    void deleteOneQuestion(long roomId, long questionId);

    /**
     * Delete all questions.
     *
     * @param roomId the room id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE FROM rooms_questions WHERE rooms_questions.room_id = ?1",
        nativeQuery = true)
    void deleteAllQuestions(long roomId);
}
