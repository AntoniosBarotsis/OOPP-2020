package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import java.util.Date;
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
     * Gets the text of the question.
     *
     * @param questionId the question id
     * @return a String of the text of the question
     */
    @Transactional
    @Query(value = "SELECT q.text FROM Question q Where q.id=?1 ")
    String getText(long questionId);


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
     * Sets the text of the question to be newQuestion.
     *
     * @param questionId  the question id
     * @param newQuestion the value of text that will be set as question's text
     */
    @Modifying
    @Transactional

    @Query(value = "UPDATE Question q SET q.text = ?2 WHERE q.id =?1")
    void setText(long questionId, String newQuestion);


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

    /**
     * Gets the author.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    @Transactional
    @Query(value = "SELECT author FROM Question q WHERE q.id = ?1")
    User getAuthor(long questionId);


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.upvotes = q.upvotes + 1 WHERE q.id = ?1")
    void upvote(long questionId);


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.upvotes = q.upvotes - 1 WHERE q.id = ?1")
    void downvote(long questionId);

    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    @Transactional
    @Query(value = "SELECT q.upvotes FROM Question q Where q.id = ?1")
    int getUpvotes(long questionId);

    /**
     * Gets the score.
     *
     * @param questionId the question id
     * @return the score value of question
     */
    @Transactional
    @Query(value = "SELECT q.score FROM Question q WHERE q.id = ?1")
    int getScore(long questionId);


    /**
     * Sets the score of question with value score.
     *
     * @param questionId the question id
     * @param score      the new score value of question
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.score = ?2 WHERE q.id = ?1")
    void setScore(long questionId, int score);


    /**
     * Gets the id of Question with the highest score.
     *
     * @return question id of highest score Question
     */
    @Transactional
    @Query(value = "SELECT q.id FROM Question q ORDER BY q.score DESC ")
    List<Long> getHighestScore();


    /**
     * Gets the date the question was created.
     *
     * @param questionId the question id
     * @return the question date
     */
    @Transactional
    @Query(value = "SELECT timeCreated FROM Question q WHERE q.id = ?1")
    Date getDate(long questionId);


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    @Transactional
    @Query(value = "SELECT q.status FROM Question q WHERE q.id = ?1")
    Question.QuestionStatus getStatus(long questionId);


    /**
     * Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=1 WHERE q.id = ?1")
    void setAnswered(long questionId);


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=2 WHERE q.id = ?1")
    void setSpam(long questionId);


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=0 WHERE q.id = ?1")
    void setOpen(long questionId);


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @Transactional
    @Query(value = "SELECT q.answer FROM Question q WHERE q.id = ?1")
    String getAnswer(long questionId);


    /**
     * Sets the answer of question as answer.
     *
     * @param questionId the question id
     * @param answer     the new answer of question
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.answer = ?2 WHERE q.id = ?1")
    void setAnswer(long questionId, String answer);

    /**
     * Sets the beingAnswered status of a question to true or false.
     * @param questionId the question to modify
     * @param answer the value to set the question field to
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.beingAnswered = ?2 WHERE q.id = ?1")
    void setBeingAnswered(long questionId, boolean answer);

    /**
     * Gets the beingAnswered field of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @Transactional
    @Query(value = "SELECT q.beingAnswered FROM Question q WHERE q.id = ?1")
    boolean getBeingAnswered(long questionId);
}
