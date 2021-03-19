package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.users.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
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

    @Transactional
    @Query(value = "SELECT * FROM USER WHERE dtype = 'Student';", nativeQuery = true)
    List<User> findAllStudents();

    @Transactional
    @Query(value = "SELECT * FROM USER WHERE dtype = 'ElevatedUser';", nativeQuery = true)
    List<User> findAllElevateUsers();
}
