package nl.tudelft.oopp.demo.repositories;

import javax.transaction.Transactional;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.Set;

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

    @Modifying
    @Transactional
    @Query(value = "INSERT INTO user_questions_upvoted (user_id, questions_upvoted_id) VALUES (?1, ?2)", nativeQuery = true)
    void addUpvotedQuestion(Long userId, Long questionId);


    @Modifying
    @Transactional
    @Query(value = "DELETE FROM user_questions_upvoted  WHERE user_id = ?1 AND questions_upvoted_id = ?2", nativeQuery = true)
    void removeUpvotedQuestion(Long userId, Long question);

    @Transactional
    @Query(value = "SELECT u.questionsUpvoted FROM User u WHERE u.id = ?1")
    HashSet<Question> getUpvotedQuestion(Long userId);
}
