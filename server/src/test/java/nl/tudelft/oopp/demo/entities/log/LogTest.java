package nl.tudelft.oopp.demo.entities.log;

import static org.junit.jupiter.api.Assertions.*;

import java.util.List;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
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

    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        u1 = new ElevatedUser("Admin", "ip", true);
        u2 = new ElevatedUser("Mod", "ip");
        u3 = new Student("Student", "ip");
        userRepository.saveAll(List.of(u1, u2, u3));

        r1 = new Room("Room Title", false, u1);
        roomRepository.save(r1);
    }

    @Test
    void getId() {
    }

    @Test
    void getUser() {
    }

    @Test
    void getRoom() {
    }

    @Test
    void getAction() {
    }

    @Test
    void getDate() {
    }

    @Test
    void setId() {
    }

    @Test
    void setUser() {
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