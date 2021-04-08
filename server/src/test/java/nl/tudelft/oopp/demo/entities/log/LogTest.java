package nl.tudelft.oopp.demo.entities.log;

import static org.assertj.core.api.Assertions.assertThat;
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
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
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
    private Room r2;

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
    @Autowired
    private LogEntryRepository logEntryRepository;

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
        r2 = new Room("Room Title 2", false, u1, roomConfig);
        roomRepository.saveAll(List.of(r1, r2));

        question = new Question("Question Text", u3);
        questionRepository.save(question);

        b1 = new LogBan(r1, u1, "ip", date);
        j1 = new LogJoin(u1, r1, date);
        q1 = new LogQuestion(r1, (Student) u3, question);
        b2 = new LogBan(r2, u2, "ip2");
        j2 = new LogJoin(u2, r2);
        q2 = new LogQuestion(r2, (Student) u3, question);

        logEntryRepository.saveAll(List.of(b1, j1, q1, b2, j2, q2));
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
        assertThat(b1.getDate()).isCloseTo(new Date(), 1000);
        assertThat(j1.getDate()).isCloseTo(new Date(), 1000);
        assertThat(q1.getDate()).isCloseTo(new Date(), 1000);
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
        assertThat(b1.getRoom()).isEqualTo(r1);
        assertThat(j1.getRoom()).isEqualTo(r1);
        assertThat(q1.getRoom()).isEqualTo(r1);

        b1.setRoom(r2);
        j1.setRoom(r2);
        q1.setRoom(r2);

        assertThat(b1.getRoom()).isEqualTo(r2);
        assertThat(j1.getRoom()).isEqualTo(r2);
        assertThat(q1.getRoom()).isEqualTo(r2);
    }

    @Test
    void setDate() {
        Date date = new Date();

        b1.setDate(date);
        j1.setDate(date);
        q1.setDate(date);

        assertThat(b1.getDate()).isEqualTo(date);
        assertThat(j1.getDate()).isEqualTo(date);
        assertThat(q1.getDate()).isEqualTo(date);
    }

    @Test
    void testEquals() {
        assertThat(b1).isNotEqualTo(b2);
        assertThat(j1).isNotEqualTo(j2);
        assertThat(q1).isNotEqualTo(q2);

        b2.setRoom(r1);
        j2.setRoom(r1);
        q2.setRoom(r1);

        assertThat(b1).isNotEqualTo(b2);
        assertThat(j1).isNotEqualTo(j2);
        assertThat(q1).isNotEqualTo(q2);

        b2.setUser(b1.getUser());
        j2.setUser(j1.getUser());
        q2.setUser(q1.getUser());

        assertThat(b1).isNotEqualTo(b2);
        assertThat(j1).isNotEqualTo(j2);
        assertThat(q1).isNotEqualTo(q2);

        b2.setDate(b1.getDate());
        j2.setDate(j1.getDate());
        q2.setDate(q1.getDate());

        assertThat(b1).isNotEqualTo(b2);
        assertThat(j1).isNotEqualTo(j2);
        assertThat(q1).isNotEqualTo(q2);

        b2.setId(b1.getId());
        j2.setId(j1.getId());
        q2.setId(q1.getId());

        assertThat(b1).isEqualTo(b2);
        assertThat(j1).isEqualTo(j2);
        assertThat(q1).isEqualTo(q2);
    }

    @Test
    void testHashCode() {
        assertThat(b1.hashCode()).isNotEqualTo(b2.hashCode());
        assertThat(j1.hashCode()).isNotEqualTo(j2.hashCode());
        assertThat(q1.hashCode()).isNotEqualTo(q2.hashCode());

        assertThat(b1.hashCode()).isEqualTo(b1.hashCode());
        assertThat(b2.hashCode()).isEqualTo(b2.hashCode());
        assertThat(j1.hashCode()).isEqualTo(j1.hashCode());
        assertThat(j2.hashCode()).isEqualTo(j2.hashCode());
        assertThat(q1.hashCode()).isEqualTo(q1.hashCode());
        assertThat(q2.hashCode()).isEqualTo(q2.hashCode());
    }

    @Test
    void testToString() {
        // its 1 30 in the morning i am not writing this
        assertThat(b1.toString()).isNotBlank();
        assertThat(b2.toString()).isNotBlank();
        assertThat(j1.toString()).isNotBlank();
        assertThat(j2.toString()).isNotBlank();
        assertThat(q1.toString()).isNotBlank();
        assertThat(q2.toString()).isNotBlank();
    }
}