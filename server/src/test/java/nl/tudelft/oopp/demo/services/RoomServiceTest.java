package nl.tudelft.oopp.demo.services;

import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

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
    private RoomConfigRepository roomConfigRepository;

    @Autowired
    private LogEntryRepository logEntryRepository;

    private ElevatedUser elevatedUser1;
    private ElevatedUser elevatedUser2;
    private Student student1;
    private RoomService roomService;

    private Room room1;
    private Room room2;
    private Set<Room> rooms;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username1", "ip", true);
        elevatedUser2 = new ElevatedUser("Username2", "ip", true);
        student1 = new Student("Username", "ip");

        userRepository.saveAll(List.of(elevatedUser1, elevatedUser2, student1));

        RoomConfig roomConfig1 = new RoomConfig();
        RoomConfig roomConfig2 = new RoomConfig();
        roomConfigRepository.saveAll(List.of(roomConfig1, roomConfig2));

        room1 = new Room("Title1", false, elevatedUser1, roomConfig1);
        room2 = new Room("Title2", false, elevatedUser2, roomConfig2);

        rooms = Set.of(room1, room2);

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

    }

    @Test
    void getPrivatePassword() {
    }

    @Test
    void findAllQuestions() {
    }

    @Test
    void deleteAllQuestions() {
    }

    @Test
    void findAllPolls() {
    }

    @Test
    void incrementTooFast() {
    }

    @Test
    void decrementTooFast() {
    }

    @Test
    void incrementTooSlow() {
    }

    @Test
    void decrementTooSlow() {
    }

    @Test
    void incrementNormalSpeed() {
    }

    @Test
    void decrementNormalSpeed() {
    }

    @Test
    void isBanned() {
    }

    @Test
    void banUser() {
    }

    @Test
    void unbanUser() {
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