package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import java.util.HashSet;
import java.util.Set;

import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<User, Long> {


    /**
     * Add question to user.
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
     * Adds questionId to the ids of question user with userId upvoted.
     *
     * @param userId the user id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_questions_upvoted (user_id, questions_upvoted_id) " +
            "VALUES (?1, ?2)", nativeQuery = true)
    void addUpvotedQuestion(Long userId, Long questionId);


    /**
     * Removes questionId from the ids of questions user with userId upvoted.
     *
     * @param userId the user id
     * @param questionId the question id
     */
    @Modifying
    @Transactional
    @Query(value = "DELETE " +
            "FROM user_questions_upvoted  " +
            "WHERE user_id = ?1 AND questions_upvoted_id = ?2", nativeQuery = true)
    void removeUpvotedQuestion(Long userId, Long questionId);

    /**
     * Gets the set of questions user with userId upvoted.
     *
     * @param userId the user id
     */
    @Transactional
    @Query(value = "SELECT u.questionsUpvoted FROM User u WHERE u.id = ?1")
    HashSet<Question> getUpvotedQuestion(Long userId);
}
