package nl.tudelft.oopp.demo.entities;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;

@DataJpaTest
class QuestionTest {
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private UserRepository userRepository;

    private Question q1;
    private Question q2;
    private ElevatedUser u1;
    private ElevatedUser u2;
    private Student u3;

    @BeforeEach
    void setUp() {
        u1 = new ElevatedUser("Admin", "ip", true);
        u2 = new ElevatedUser("Mod", "ip");
        u3 = new Student("Student", "ip");
        userRepository.saveAll(List.of(u1, u2, u3));

        q1 = new Question("Question text", u1);
        q2 = new Question("Question text 2", u2);
        questionRepository.saveAll(List.of(q1, q2));
    }

    @Test
    void exportToTxt() {
        assertThat(q1.exportToTxt()).isEqualTo("TO BE IMPLEMENTED");
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void exportToJson() throws JsonProcessingException {
        assertThat(q1.exportToJson()).isEqualTo("{\"id\":1,\"text\":\"Question text\",\"author\":"
            + "{\"id\":1,\"username\":\"Admin\",\"questionsAsked\":[],\"questionsUpvoted\":[],"
            + "\"type\":\"ADMIN\"},\"upvotes\":0,\"score\":0,\"timeCreated\":"
            + q1.getTimeCreated().getTime() + ",\"" + "status\":\"OPEN\",\"answer\":null,\""
            + "answered\"" + ":false}");
    }

    @Test
    void isAnswered() {
        assertThat(q1.isAnswered()).isEqualTo(false);
        q1.setAnswer("answer");
        assertThat(q1.isAnswered()).isEqualTo(true);
    }

    @Test
    void statusToFactor() {
        assertThat(q1.statusToFactor()).isEqualTo(0);
        q1.setStatus(Question.QuestionStatus.ANSWERED);
        assertThat(q1.statusToFactor()).isEqualTo(1);
        q1.setStatus(Question.QuestionStatus.SPAM);
        assertThat(q1.statusToFactor()).isEqualTo(2);
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void getId() {
        assertThat(q1.getId()).isEqualTo(1L);
    }

    @Test
    void getText() {
        q1.setId(2L);
        assertThat(q1.getId()).isEqualTo(2L);
    }

    @Test
    void getAuthor() {
        assertThat(q1.getAuthor()).isEqualTo(u1);
    }

    @Test
    void getUpvotes() {
        assertThat(q1.getUpvotes()).isEqualTo(0);
    }

    @Test
    void getScore() {
        assertThat(q1.getScore()).isEqualTo(0);
    }

    @Test
    void getTimeCreated() {
        assertThat(q1.getTimeCreated()).isCloseTo(new Date(), 1000);
    }

    @Test
    void getStatus() {
        assertThat(q1.getStatus()).isEqualTo(Question.QuestionStatus.OPEN);
    }

    @Test
    void getAnswer() {
        assertThat(q1.getAnswer()).isEqualTo(null);
    }

    @Test
    void setId() {
        q1.setId(2L);
        assertThat(q1.getId()).isEqualTo(2L);
    }

    @Test
    void setText() {
        q1.setText("text");
        assertThat(q1.getText()).isEqualTo("text");
    }

    @Test
    void setAuthor() {
        q1.setAuthor(u2);
        assertThat(q1.getAuthor()).isEqualTo(u2);
    }

    @Test
    void setUpvotes() {
        q1.setUpvotes(1);
        assertThat(q1.getUpvotes()).isEqualTo(1);
    }

    @Test
    void setScore() {
        q1.setScore(1);
        assertThat(q1.getScore()).isEqualTo(1);
    }

    @Test
    void setTimeCreated() {
        Date date = new Date(42L);
        q1.setTimeCreated(date);
        assertThat(q1.getTimeCreated()).isInSameDayAs(date);
    }

    @Test
    void setStatus() {
        q1.setStatus(Question.QuestionStatus.ANSWERED);
        assertThat(q1.getStatus()).isEqualTo(Question.QuestionStatus.ANSWERED);
    }

    @Test
    void setAnswer() {
        q1.setAnswer("answer");
        assertThat(q1.getAnswer()).isEqualTo("answer");
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void testEquals() {
        assertThat(q1).isNotEqualTo(q2);
        q1.setAuthor(u2);
        assertThat(q1).isNotEqualTo(q2);
        q1.setText("Question text 2");
        assertThat(q1).isNotEqualTo(q2);
        q1.setId(2);
        assertThat(q1).isEqualTo(q2);
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void testHashCode() {
        assertThat(q1.hashCode()).isEqualTo(q1.hashCode());
        assertThat(q1.hashCode()).isNotEqualTo(q2.hashCode());
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void testToString() {
        assertThat(q1.toString()).isEqualTo("Question(id=1, text=Question text, author=User(id=1, "
            + "username=Admin, ip=ip, questionsAsked=[], questionsUpvoted=[], type=ADMIN), "
            + "upvotes=0, score=0, timeCreated=" + q1.getTimeCreated() + ", status=OPEN, "
            + "answer=null)");
    }
}