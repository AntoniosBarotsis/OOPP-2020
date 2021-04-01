package nl.tudelft.oopp.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;



@DataJpaTest
class UserRepositoryTest {

    @Autowired
    private UserRepository repository;

    @Autowired
    private QuestionRepository questionRepository;

    private long id1;
    private long id2;

    private long questionId1;
    private long questionId2;

    private User user1;
    private User user2;

    private Question question1;
    private Question question2;


    @BeforeEach
    void setUp() {

        user1 = new Student("UserName 1", "IP 1");
        user2 = new Student("UserName 2", "IP 2");

        question1 = new Question("This is the text 1", user1);
        question2 = new Question("This is the text 2", user2);


        repository.save(user1);
        repository.save(user2);

        questionRepository.save(question1);
        questionRepository.save(question2);


        id1 = user1.getId();
        id2 = user2.getId();

        questionId1 = question1.getId();
        questionId2 = question2.getId();

    }


    @Test
    void addUpvotedQuestion() {
        HashSet<Question> set = new HashSet<Question>();

        assertEquals(repository.getUpvotedQuestion(id1), set);
        repository.addUpvotedQuestion(id1, questionId1);
        set.add(question1);
        assertEquals(repository.getUpvotedQuestion(id1), set);
    }

    @Test
    void removeUpvotedQuestion() {
        HashSet<Question> set = new HashSet<Question>();

        repository.addUpvotedQuestion(id1, questionId1);
        set.add(question1);
        assertEquals(repository.getUpvotedQuestion(id1), set);

        set.remove(question1);

        repository.removeUpvotedQuestion(id1, questionId1);

        assertEquals(repository.getUpvotedQuestion(id1), set);

    }

    @Test
    void getUpvotedQuestion() {
        HashSet<Question> set = new HashSet<Question>();

        assertEquals(repository.getUpvotedQuestion(id1), set);
        repository.addUpvotedQuestion(id1, questionId1);
        repository.addUpvotedQuestion(id1, questionId2);

        set.add(question1);
        set.add(question2);
        assertEquals(repository.getUpvotedQuestion(id1), set);
    }
}