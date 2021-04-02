package nl.tudelft.oopp.demo.entities;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
public class PollTest {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RoomConfigRepository roomConfigRepository;

    private Room r1;
    private Student s1;
    private ElevatedUser e1;
    private Poll p1;

    @BeforeEach
    void setUp() {
        e1 = new ElevatedUser("Admin", "ip", true);
        s1 = new Student("Student", "ip");

        userRepository.saveAll(List.of(e1, s1));

        p1 = new Poll("Poll text", List.of("Wrong answer1", "Wrong answer2",
                "Wrong answer3", "Wrong answer4", "Correct answer1", "Correct answer2"),
                List.of("Correct answer1", "Correct answer2"));

        pollRepository.saveAll(List.of(p1));

        r1 = new Room("Room title", false, e1);

        r1.setPolls(Stream.of(p1)
                .collect(Collectors.toSet())
        );

        RoomConfig roomConfig = new RoomConfig();
        r1.setRoomConfig(roomConfig);

        roomConfigRepository.save(roomConfig);
        roomRepository.save(r1);
    }

    @Test
    void injectedComponentsAreNotNull() {
        assertThat(roomRepository).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(pollRepository).isNotNull();
        assertThat(answerRepository).isNotNull();
        assertThat(roomConfigRepository).isNotNull();
    }

    @Test
    void getText() {
        assertThat(p1.getText()).isEqualTo("Poll text");
    }

    @Test
    void setText() {
        p1.setText("Poll text 2");
        assertThat(p1.getText()).isEqualTo("Poll text 2");
    }

    @Test
    void getOptions() {
        List<String> options = List.of("Wrong answer1", "Wrong answer2",
                "Wrong answer3", "Wrong answer4", "Correct answer1", "Correct answer2");
        assertThat(options).isEqualTo(p1.getOptions());
    }

    @Test
    void setOptions() {
        List<String> options = List.of("Wrong answer5", "Correct answer3");
        p1.setOptions(options);

        assertThat(options).isEqualTo(p1.getOptions());
    }

    @Test
    void getCorrectAnswer() {
        List<String> correctAnswer = List.of("Correct answer1", "Correct answer2");
        assertThat(correctAnswer).isEqualTo(p1.getCorrectAnswer());
    }

    @Test
    void setCorrectAnswer() {
        List<String> correctAnswer = List.of("Correct answer3");
        p1.setCorrectAnswer(correctAnswer);

        assertThat(correctAnswer).isEqualTo(p1.getCorrectAnswer());
    }

    @Test
    void getStatus() {
        assertThat(p1.getStatus()).isEqualTo(Poll.PollStatus.OPEN);
    }

    @Test
    void setStatus() {
        p1.setStatus(Poll.PollStatus.CLOSED);
        assertThat(p1.getStatus()).isEqualTo(Poll.PollStatus.CLOSED);
    }
}
