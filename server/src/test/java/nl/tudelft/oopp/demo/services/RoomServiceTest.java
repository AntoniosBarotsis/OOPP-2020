package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class RoomServiceTest {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RoomConfigRepository roomConfigRepository;

    @Autowired
    private LogEntryRepository logEntryRepository;

    private ElevatedUser elevatedUser1;
    private ElevatedUser elevatedUser2;
    private Student student1;
    private Student student2;
    private RoomService roomService;

    private Question question1;
    private Room room1;
    private Room room2;
    private Set<Room> rooms;
    private Poll poll1;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username1", "ip", true);
        elevatedUser2 = new ElevatedUser("Username2", "ip", true);
        student1 = new Student("Username1", "ip");
        student2 = new Student("Username2", "ip");

        userRepository.saveAll(List.of(elevatedUser1, elevatedUser2, student1, student2));

        RoomConfig roomConfig1 = new RoomConfig();
        RoomConfig roomConfig2 = new RoomConfig();
        roomConfigRepository.saveAll(List.of(roomConfig1, roomConfig2));

        room1 = new Room("Title1", false, elevatedUser1, roomConfig1);
        room2 = new Room("Title2", false, elevatedUser2, roomConfig2);

        question1 = new Question("This is the text 1", student1);
        questionRepository.save(question1);
        room1.setQuestions(Set.of(question1));

        poll1 = new Poll("Text1", List.of("A", "B", "Correct answer"),
                List.of("Correct answer"));
        pollRepository.save(poll1);
        room1.setPolls(Set.of(poll1));

        rooms = Set.of(room1, room2);

        room1.setBannedIps(Set.of("ip"));

        roomRepository.saveAll(rooms);

        roomService = new RoomService(roomRepository, userRepository, logEntryRepository, roomConfigRepository);
    }

    @Test
    void findAll() {
        assertEquals(rooms, Set.copyOf(roomService.findAll()));
    }

    @Test
    void getOne() {
        assertEquals(room1, roomService.getOne(room1.getId()));
    }

    @Test
    void getPublicPassword() {
        assertEquals(room1.getNormalPassword(), roomService.getPublicPassword(room1.getId()));
    }

    @Test
    void getPrivatePassword() {
        assertEquals(room1.getElevatedPassword(), roomService.getPrivatePassword(room1.getId(), "ip"));
    }

    @Test
    void findAllQuestions() {
        assertEquals(room1.getQuestions(), roomService.findAllQuestions(room1.getId()));
    }

    @Test
    void deleteAllQuestions() {
        roomService.deleteAllQuestions(room1.getId(), elevatedUser1.getId());
        assertTrue(roomService.findAllQuestions(room1.getId()).isEmpty());
    }

    @Test
    void findAllPolls() {
        assertEquals(Set.of(poll1), roomService.findAllPolls(room1.getId()));
    }

    @Test
    void isBanned() {
        assertTrue(roomService.isBanned(room1.getId(), student2.getId()));
    }

    @Test
    void exportLog() {
    }

    @Test
    void setOngoing() {
    }

    @Test
    void startEndLecture() {
    }

    @Test
    void setConfig() {
    }

    @Test
    void isNotAuthorized() {
    }

    @Test
    void testIsNotAuthorized() {
    }

    @Test
    void createRoom() {
    }

    @Test
    void join() {
    }

    @Test
    void getRoom() {
    }

    @Test
    void isOngoing() {
    }

    @Test
    void refreshOngoing() {
    }
}