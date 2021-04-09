package nl.tudelft.oopp.demo.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RoomConfigRepositoryTest {

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private RoomConfigRepository roomConfigRepository;

    private ElevatedUser elevatedUser1;
    private Student student1;

    private Room room1;
    private Room room2;
    private RoomConfig settings1;
    private long id;
    private long configId;

    private Set<Question> questions;
    private Set<Poll> polls;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username", "ip", true);
        student1 = new Student("Username", "ip");

        userRepository.saveAll(List.of(elevatedUser1, student1));

        settings1 = new RoomConfig();
        configId = settings1.getId();
        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(settings1);

        room1 = new Room("Title", false, elevatedUser1, roomConfig);
        room2 = new Room("Title2", true, elevatedUser1, roomConfig);

        Question question1 = new Question("Text1", student1);
        Question question2 = new Question("Text2", student1);
        questions = Set.of(question1, question2);
        questionRepository.saveAll(questions);

        Poll poll1 = new Poll("Text1", List.of("A", "B", "Correct answer"),
                List.of("Correct answer"));
        Poll poll2 = new Poll("Text2", List.of("A", "B", "Correct answer", "D"),
                List.of("Correct answer"));
        polls = Set.of(poll1, poll2);
        pollRepository.saveAll(polls);

        room1.setQuestions(questions);
        room1.setPolls(polls);

        // roomRepository.save(room1);
        id = room1.getId();
    }

    @Test
    void setConfig() {
        settings1.setStudentRefreshRate(2);
        settings1.setModRefreshRate(2);
        settings1.setQuestionCooldown(100);
        settings1.setPaceCooldown(100);
        room2.setRoomConfig(settings1);

        roomConfigRepository.setConfig(configId,2, 2, 100, 100);
        assertThat(roomConfigRepository.getOne(configId)).isEqualTo(room2.getRoomConfig());
    }
}