package nl.tudelft.oopp.demo.entities.log;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;


import java.util.Date;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@DataJpaTest
@ExtendWith(SpringExtension.class)
class LogTest {
    private LogBan b1;
    private LogBan b2;
    private LogJoin j1;
    private LogJoin j2;
    private LogQuestion q1;
    private LogQuestion q2;

    private ElevatedUser u1;
    private User u2;
    private User u3;

    private Room r1;

    private Question question;

    private Date date;

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private RoomConfigRepository roomConfigRepository;

    @BeforeEach
    void setUp() {
        u1 = new ElevatedUser("Admin", "ip", true);
        u2 = new ElevatedUser("Mod", "ip");
        u3 = new Student("Student", "ip");

        userRepository.saveAll(List.of(u1, u2, u3));

        date = new Date();

        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(roomConfig);

        r1 = new Room("Room Title", false, u1, roomConfig);
        roomRepository.save(r1);

        question = new Question("Question Text", u3);
        b1 = new LogBan(r1, u1, "ip", date);

        j1 = new LogJoin(u1, r1, date);

        q1 = new LogQuestion(r1, (Student) u3, question);

    }

    @Test
    void getId() {
        assertNotNull(b1.getId());
        assertNotNull(j1.getId());
        assertNotNull(q1.getId());

    }

    @Test
    void getUser() {
        assertEquals(u1, b1.getUser());
        assertEquals(u1, j1.getUser());
        assertEquals(u3, q1.getUser());
    }

    @Test
    void getRoom() {
        assertEquals(r1, b1.getRoom());
        assertEquals(r1, j1.getRoom());
        assertEquals(r1, q1.getRoom());

    }

    @Test
    void getAction() {
        assertEquals(LogEntry.ActionType.BANNED, b1.getAction());
        assertEquals(LogEntry.ActionType.JOINED, j1.getAction());
        assertEquals(LogEntry.ActionType.ASKED, q1.getAction());
    }

    @Test
    void getDate() {
        assertEquals(date, b1.getDate());
        assertEquals(date, j1.getDate());
        assertEquals(date, q1.getDate());
    }

    @Test
    void setId() {
        b1.setId(0);
        j1.setId(1);
        q1.setId(2);

        assertEquals(0, b1.getId());
        assertEquals(1, j1.getId());
        assertEquals(2, q1.getId());
    }

    @Test
    void setUser() {
        User secondElevatedUser = new ElevatedUser("Admin", "ip", true);
        User secondStudent = new Student("Student", "ip");

        b1.setUser(secondElevatedUser);
        j1.setUser(secondElevatedUser);
        q1.setUser(secondStudent);

        assertEquals(secondElevatedUser, b1.getUser());
        assertEquals(secondElevatedUser, j1.getUser());
        assertEquals(secondStudent, q1.getUser());

    }

    @Test
    void setRoom() {
    }

    @Test
    void setAction() {
    }

    @Test
    void setDate() {
    }

    @Test
    void testEquals() {
    }

    @Test
    void canEqual() {
    }

    @Test
    void testHashCode() {
    }

    @Test
    void testToString() {
    }
}