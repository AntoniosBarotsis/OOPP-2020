package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;



/**
 * Question repository.
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
    String getQuestion(long questionId);


    /**
     * Sets the text of the question to be newQuestion.
     *
     * @param questionId the question id
     * @param newQuestion the value of text that will be set as question's text
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.text = ?2 WHERE q.id =?1")
    void editQuestion(long questionId, String newQuestion);


    /**
     * Gets the author.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    @Transactional
    @Query(value = "SELECT author FROM Question q WHERE q.id=?1")
    User getAuthor(long questionId);


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.upvotes = q.upvotes + 1 WHERE q.id =?1")
    void incrementUpvotes(long questionId);


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.upvotes = q.upvotes - 1 WHERE q.id =?1")
    void downvote(long questionId);


    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    @Transactional
    @Query(value = "SELECT q.upvotes FROM Question q Where q.id =?1")
    int getUpvotes(long questionId);


    /**
     * Gets the score.
     *
     * @param questionId the question id
     * @return the score value of question
     */
    @Transactional
    @Query(value = "SELECT q.score FROM Question q WHERE q.id=?1")
    int getScore(long questionId);


    /**
     * Sets the score of question with value score.
     *
     * @param questionId the question id
     * @param score the new score value of question
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.score = ?2 WHERE q.id=?1")
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
     * Gets the date.
     *
     * @param questionId the question id
     * @return the question date
     */
    @Transactional
    @Query(value = "SELECT timeCreated FROM Question q WHERE q.id=?1")
    Date getTime(long questionId);


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    @Transactional
    @Query(value = "SELECT q.status FROM Question q WHERE q.id=?1")
    Enum getStatus(long questionId);


    /**
     *Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=1 WHERE q.id =?1")
    void isAnswered(long questionId);


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=2 WHERE q.id =?1")
    void isSpam(long questionId);


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.status=0 WHERE q.id =?1")
    void isOpen(long questionId);


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @Transactional
    @Query(value = "SELECT q.answer FROM Question q WHERE q.id=?1")
    String getAnswer(long questionId);


    /**
     * Sets the answer of question as answer.
     *
     * @param questionId the question id
     * @param answer the new answer of question
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.answer = ?2 WHERE q.id = ?1")
    void setAnswer(long questionId, String answer);


    /**
     * Gets the title of question.
     *
     * @param questionId the question id
     * @return the title of question
     */
    @Transactional
    @Query(value = "SELECT q.title FROM Question q WHERE q.id=?1")
    String getTitle(long questionId);


    /**
     * Sets the title of question as title.
     *
     * @param questionId the question id
     * @param title the new title of question
     */
    @Modifying
    @Transactional
    @Query(value = "UPDATE Question q SET q.title = ?2 WHERE q.id=?1")
    void setTitle(long questionId, String title);


    /**
     * Deletes question.
     *
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE from Question q WHERE q.id=?1")
    void delete(long questionId);
}
