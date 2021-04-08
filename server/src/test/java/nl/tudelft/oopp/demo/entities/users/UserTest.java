package nl.tudelft.oopp.demo.entities.users;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class UserTest {

    private Room r1;
    private Student s1;
    private ElevatedUser e1;
    private ElevatedUser e2;
    private Poll p1;
    private Question q1;

    @BeforeEach
    void setUp() {
        e1 = new ElevatedUser("Admin", "ip", true);
        e2 = new ElevatedUser("Moderator", "ip", false);
        s1 = new Student("Student", "ip");

        p1 = new Poll("Poll text", List.of("Wrong answer1", "Wrong answer2",
                "Wrong answer3", "Wrong answer4", "Correct answer1", "Correct answer2"),
                List.of("Correct answer1", "Correct answer2"));

        r1 = new Room("Room title", false, e1);

        r1.setPolls(Stream.of(p1)
                .collect(Collectors.toSet())
        );

        q1 = new Question("Question", e1);

        RoomConfig roomConfig = new RoomConfig();
        r1.setRoomConfig(roomConfig);

    }

    @Test
    void testEquals() {
        Student b = new Student("Student", "ip");
        ElevatedUser c = new ElevatedUser("Admin", "ip");
        assertThat(s1.equals(b));
        assertEquals(s1, b);
        assertThat(e1.equals(c));
    }

    @Test
    void testHashCode() {
        assertThat(s1.hashCode()).isNotNull();
        Student b = new Student("Student", "ip");
        assertThat(s1.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void typeToString() {
        assertThat(e1.typeToString()).isEqualTo("LECTURER");
        assertThat(e2.typeToString()).isEqualTo(User.Type.MODERATOR.toString());
        assertThat(s1.typeToString()).isEqualTo(User.Type.STUDENT.toString());
    }

    @Test
    void getId() {
        assertThat(e1.getId()).isNotNull();
    }

    @Test
    void getUsername() {
        assertThat("Admin").isEqualTo(e1.getUsername());
        assertThat("Moderator").isEqualTo(e2.getUsername());
        assertThat("Student").isEqualTo(s1.getUsername());
    }

    @Test
    void getIp() {
        assertThat("ip").isEqualTo(e1.getIp());
        assertThat("ip").isEqualTo(e2.getIp());
        assertThat("ip").isEqualTo(s1.getIp());
    }

    @Test
    void getQuestionsAsked() {
        assertThat(s1.getQuestionsAsked().isEmpty()).isTrue();
        Set<Question> questions = new HashSet<>();
        Set<Question> questions2 = new HashSet<>();

        questions.add(q1);
        questions2.add(q1);
        s1.setQuestionsAsked(questions);

        assertThat(questions2).isEqualTo(s1.getQuestionsAsked());
    }

    @Test
    void getQuestionsUpvoted() {
        assertThat(s1.getQuestionsUpvoted().isEmpty()).isTrue();
        Set<Question> questions = new HashSet<>();
        Set<Question> questions2 = new HashSet<>();

        questions.add(q1);
        questions2.add(q1);
        s1.setQuestionsUpvoted(questions);

        assertThat(questions2).isEqualTo(s1.getQuestionsUpvoted());
    }

    @Test
    void getType() {
        assertThat(User.Type.ADMIN).isEqualTo(e1.getType());
        assertThat(User.Type.MODERATOR).isEqualTo(e2.getType());
        assertThat(User.Type.STUDENT).isEqualTo(s1.getType());
    }

    @Test
    void setId() {
        int newId = 1;
        s1.setId(newId);

        assertThat(newId).isEqualTo(s1.getId());
    }

    @Test
    void setUsername() {
        String newUsername = "Daniel";
        s1.setUsername(newUsername);

        assertThat(newUsername).isEqualTo(s1.getUsername());
    }

    @Test
    void setIp() {
        String newIp = "123.123.123.123";
        s1.setIp(newIp);

        assertThat(newIp).isEqualTo(s1.getIp());
    }

    @Test
    void setQuestionsAsked() {
        Set<Question> questionAsked = new HashSet<>();
        questionAsked.add(new Question("question", s1));
        s1.setQuestionsAsked(questionAsked);

        assertThat(questionAsked).isEqualTo(s1.getQuestionsAsked());
    }

    @Test
    void setQuestionsUpvoted() {
        Set<Question> questionUpvoted = new HashSet<>();
        questionUpvoted.add(new Question("question", s1));
        s1.setQuestionsUpvoted(questionUpvoted);

        assertThat(questionUpvoted).isEqualTo(s1.getQuestionsUpvoted());
    }

    @Test
    void setType() {
        User.Type a = User.Type.ADMIN;
        s1.setType(a);

        assertThat(User.Type.ADMIN).isEqualTo(s1.getType());
    }

    @Test
    void testToString() {
        String expected = "User(id=0, username=Student, ip=ip, questionsAsked=[], questionsUpvoted=[], type=STUDENT)";
        assertThat(s1.toString()).isEqualTo(expected);
    }
}