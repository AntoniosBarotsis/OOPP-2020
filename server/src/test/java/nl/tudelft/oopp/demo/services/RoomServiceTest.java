package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.RoomHelper;
import nl.tudelft.oopp.demo.entities.log.LogBan;
import nl.tudelft.oopp.demo.entities.log.LogCollection;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
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
        elevatedUser1 = new ElevatedUser("Username1", "elevated-ip", true);
        elevatedUser2 = new ElevatedUser("Username2", "elevated-ip", true);
        student1 = new Student("Username1", "normal-ip");
        student2 = new Student("Username2", "banned-ip");

        userRepository.saveAll(List.of(elevatedUser1, elevatedUser2, student1, student2));

        RoomConfig roomConfig1 = new RoomConfig();
        RoomConfig roomConfig2 = new RoomConfig();
        roomConfigRepository.saveAll(List.of(roomConfig1, roomConfig2));

        room1 = new Room("Title1", false, elevatedUser1, roomConfig1);
        room2 = new Room("Title2", false, elevatedUser2, roomConfig2);

        question1 = new Question("This is the text 1", student1);
        questionRepository.save(question1);
        Set<Question> questions = new HashSet<>();
        questions.add(question1);
        room1.setQuestions(questions);

        poll1 = new Poll("Text1", List.of("A", "B", "Correct answer"),
                List.of("Correct answer"));
        pollRepository.save(poll1);
        Set<Poll> polls = new HashSet<>();
        polls.add(poll1);
        room1.setPolls(polls);

        rooms = Set.of(room1, room2);

        Set<String> ips = new HashSet<>();
        ips.add("banned-ip");
        room1.setBannedIps(ips);

        roomRepository.saveAll(rooms);

        roomService = new RoomService(roomRepository, userRepository, logEntryRepository,
                roomConfigRepository);
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
        assertEquals(room1.getElevatedPassword(),
                roomService.getPrivatePassword(room1.getId(), "elevated-ip"));
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
    void exportLogAndBanUser() {
        boolean contains = false;
        roomService.banUser(room1.getId(), elevatedUser1.getId(), student2.getId(),
                room1.getElevatedPassword());
        LogCollection logCollection = roomService.exportLog(room1.getId(), "elevated-ip");
        for (LogBan logBan : logCollection.getBans()) {
            if (logBan.getIp().equals("banned-ip")) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    @Test
    void setOngoing() {
        roomService.setOngoing(room1.getId(), false, elevatedUser1.getId());
        assertFalse(room1.isOngoing());
    }

    @Test
    void startEndLecture() {
        boolean isOngoing = room1.isOngoing();
        roomService.startEndLecture(room1.getId(), elevatedUser1.getId());
        assertTrue(room1.isOngoing() != isOngoing);
    }

    @Test
    void isNotAuthorizedIp() {
        assertTrue(roomService.isNotAuthorized(room1.getId(), "normal-ip"));
        assertFalse(roomService.isNotAuthorized(room1.getId(), "elevated-ip"));
    }

    @Test
    void isNotAuthorizedId() {
        assertTrue(roomService.isNotAuthorized(room1.getId(), student1.getId()));
        assertFalse(roomService.isNotAuthorized(room1.getId(), elevatedUser1.getId()));
    }

    @Test
    void createRoom() {
        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(roomConfig);
        RoomHelper roomHelper = new RoomHelper("Title", "Username", false,
                roomConfig, new Date(), new Date(new Date().getTime() + 3600000));
        Room room = roomService.createRoom(roomHelper, "ip");
        assertEquals(room, roomService.getOne(room.getId()));
    }

    @Test
    void join() {
        User user = roomService.join(room1.getNormalPassword(), "Username3", "IP3");
        assertEquals(user, userRepository.getOne(user.getId()));

    }

    @Test
    void getRoom() {
        assertEquals(room1, roomService.getRoom(room1.getNormalPassword()));
    }

    @Test
    void isOngoing() throws JsonProcessingException {
        String json = roomService.isOngoing(room1.getId());
        JsonNode jsonNode = new ObjectMapper().readTree(json);
        assertTrue(jsonNode.get("ongoing").asBoolean());
    }

    @Test
    void refreshOngoing() {
        room1.setEndingDate(new Date(new Date().getTime() - 100));
        roomService.refreshOngoing(room1.getId());
        assertFalse(room1.isOngoing());
    }
}