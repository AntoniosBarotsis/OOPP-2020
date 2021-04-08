package nl.tudelft.oopp.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.log.LogBan;
import nl.tudelft.oopp.demo.entities.log.LogJoin;
import nl.tudelft.oopp.demo.entities.log.LogQuestion;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class LogEntryRepositoryTest {
    private LogBan b1;
    private LogJoin j1;
    private LogQuestion q1;

    private ElevatedUser u1;
    private User u2;

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
    @Autowired
    private LogEntryRepository logEntryRepository;


    @BeforeEach
    void setUp() {
        u1 = new ElevatedUser("Admin", "ip", true);
        u2 = new Student("Student", "ip");

        userRepository.saveAll(List.of(u1, u2));

        date = new Date();

        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(roomConfig);

        r1 = new Room("Room Title", false, u1, roomConfig);
        roomRepository.save(r1);

        question = new Question("Question Text", u2);
        b1 = new LogBan(r1, u1, "ip", date);

        j1 = new LogJoin(u1, r1, date);

        q1 = new LogQuestion(r1, (Student) u2, question);

        questionRepository.save(question);

        logEntryRepository.save(b1);
        logEntryRepository.save(j1);
        logEntryRepository.save(q1);


    }

    @Test
    void findAllBans() {
        List<LogBan> logBans = new LinkedList<>();
        logBans.add(b1);
        assertEquals(logBans, logEntryRepository.findAllBans(r1.getId()));
    }

    @Test
    void findAllJoins() {
        List<LogJoin> logJoins = new LinkedList<>();
        logJoins.add(j1);
        assertEquals(logJoins, logEntryRepository.findAllJoins(r1.getId()));
    }

    @Test
    void findAllQuestions() {
        List<LogQuestion> logQuestions = new LinkedList<>();
        logQuestions.add(q1);
        assertEquals(logQuestions, logEntryRepository.findAllQuestions(r1.getId()));
    }
}
