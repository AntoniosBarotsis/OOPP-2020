package nl.tudelft.oopp.demo.repositories;

import java.util.HashSet;
import java.util.List;
import javax.transaction.Transactional;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;


/**
 * The interface User repository.
 */
@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Adds a question to the user's asked questions.
     *
     * @param userId           the user id
     * @param questionsAskedId the questions asked id
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_questions_asked (user_id, questions_asked_id) VALUES (?1, ?2)",
            nativeQuery = true)
    void addQuestionToUser(long userId, long questionsAskedId);

    /**
     * Adds a question to the user's upvoted questions.
     *
     * @param userId     the user id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_questions_upvoted (user_id, questions_upvoted_id) "
            + "VALUES (?1, ?2)", nativeQuery = true)
    void addUpvotedQuestion(Long userId, Long questionId);


    /**
     * Removes a question from the user's asked questions.
     *
     * @param userId     the user id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE "
            + "FROM user_questions_upvoted "
            + "WHERE user_id = ?1 AND questions_upvoted_id = ?2", nativeQuery = true)
    void removeUpvotedQuestion(Long userId, Long questionId);

    /**
     * Gets the set of questions the user has upvoted.
     *
     * @param userId the user id
     * @return the upvoted question
     */
    @Transactional
    @Query(value = "SELECT u.questionsUpvoted FROM User u WHERE u.id = ?1")
    HashSet<Question> getUpvotedQuestion(Long userId);

    /**
     * Finds all students.
     *
     * @return the list
     */
    @Transactional
    @Query(value = "SELECT * FROM USER WHERE dtype = 'Student';", nativeQuery = true)
    List<User> findAllStudents();

    /**
     * Find all elevated users.
     *
     * @return the list
     */
    @Transactional
    @Query(value = "SELECT * FROM USER WHERE dtype = 'ElevatedUser';", nativeQuery = true)
    List<User> findAllElevateUsers();
}
