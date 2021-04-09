package nl.tudelft.oopp.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

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
public class RoomRepositoryTest {

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
    private long id;

    private Set<Question> questions;
    private Set<Poll> polls;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username", "ip", true);
        student1 = new Student("Username", "ip");

        userRepository.saveAll(List.of(elevatedUser1, student1));

        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(roomConfig);

        room1 = new Room("Title", false, elevatedUser1, roomConfig);

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

        roomRepository.save(room1);
        id = room1.getId();
    }

    @Test
    void getPublicPassword() {
        assertEquals(room1.getNormalPassword(), roomRepository.getPublicPassword(room1.getId()));
    }

    @Test
    void getPrivatePassword() {
        assertEquals(room1.getElevatedPassword(), roomRepository.getPrivatePassword(room1.getId()));
    }

    @Test
    void findAllQuestions() {
        assertEquals(questions, roomRepository.findAllQuestions(room1.getId()));
    }

    @Test
    void findAllPolls() {
        assertEquals(polls, roomRepository.findAllPolls(room1.getId()));
    }

    @Test
    void getElevatedRoomId() {
        assertEquals(id, roomRepository.getElevatedRoomId(room1.getElevatedPassword()));
    }

    @Test
    void getNormalRoomId() {
        assertEquals(room1.getId(), roomRepository.getNormalRoomId(room1.getNormalPassword()));
    }
}
