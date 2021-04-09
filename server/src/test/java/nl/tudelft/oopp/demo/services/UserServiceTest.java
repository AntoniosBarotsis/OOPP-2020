package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Set;
import java.util.Date;
import java.util.HashSet;
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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
public class UserServiceTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private RoomConfigRepository roomConfigRepository;


    private UserService userService;

    private Room room;
    private User elevatedUser;
    private User student;
    private Question question;

    private Date date;

    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);

        student = new Student("UserName 1", "IP 1");
        elevatedUser = new ElevatedUser("Admin", "IP 2", true);


        question = new Question("This is the text 1", student);

        userRepository.saveAll(List.of(student, elevatedUser));

        date = new Date();

        RoomConfig roomConfig = new RoomConfig();
        room = new Room("Room Title", false, (ElevatedUser) elevatedUser, roomConfig);

        roomConfigRepository.save(roomConfig);

        roomRepository.save(room);

        question = new Question("Question Text", student);
        questionRepository.save(question);
    }

    @Test
    void add() {
        Student student2 = new Student("UserName 2", "IP 1");
        assertEquals (userService.add(student2), student2.getId());
    }

    @Test
    void invalidAuthorId() {
        Student student2 = new Student("UserName 2", "IP 1");
        assertTrue(userService.isInvalidAuthorId(student2));
    }

    @Test
    void isInvalidAuthorIdWrongAuthor() {
        Student student2 = new Student("UserName 2", "IP 1");
        student2.setId(student.getId());
        assertTrue(userService.isInvalidAuthorId(student2));
    }

    @Test
    void isInvalidAuthorIdValidUser() {
        assertFalse(userService.isInvalidAuthorId(student));
    }

    @Test
    void findAll() {
        Set<User> expected = new HashSet<>();
        expected.add(student);
        expected.add(elevatedUser);

        Set<User> actual = new HashSet<>(userService.findAll());

        assertEquals(expected, actual);

    }

    @Test
    void addUpvotedQuestion() {
        assertEquals(new HashSet<>(), userService.getUpvotedQuestion(student.getId()));
        userService.addUpvotedQuestion(student.getId(), question.getId());
        Set<Question> expected = new HashSet();
        expected.add(question);
        assertEquals(expected, userService.getUpvotedQuestion(student.getId()));
        userService.addUpvotedQuestion(student.getId(), question.getId());
        assertEquals(expected, userService.getUpvotedQuestion(student.getId()));


    }

    @Test
    void removeUpvotedQuestionEmpty() {
        assertDoesNotThrow( () -> userService.removeUpvotedQuestion(student.getId(), question.getId()));
    }

    @Test
    void removeUpvotedQuestion() {
        assertEquals(new HashSet<>(), userService.getUpvotedQuestion(student.getId()));
        userService.addUpvotedQuestion(student.getId(), question.getId());
        Set<Question> expected = new HashSet();
        expected.add(question);
        assertEquals(expected, userService.getUpvotedQuestion(student.getId()));
        userService.removeUpvotedQuestion(student.getId(), question.getId());
        assertEquals(new HashSet<>(), userService.getUpvotedQuestion(student.getId()));
    }

    @Test
    void getUpvotedQuestion() {
        assertEquals(new HashSet<>(), userService.getUpvotedQuestion(student.getId()));
    }

    @Test
    void findAllStudents() {
        Set<User> expected = new HashSet<>();
        expected.add(student);
        expected.add(elevatedUser);
        assertEquals(expected, new HashSet<>(userService.findAll()));
    }

    @Test
    void findAllElevatedUsers() {
        Set<User> expected = new HashSet<>();
        expected.add(elevatedUser);
        assertEquals(expected, new HashSet<>(userService.findAllElevatedUsers()));
    }

    @Test
    void getElevatedNotElevatedUser() {
        assertNull(userService.getElevated(1));
        assertNull(userService.getElevated(student.getId()));
    }

    @Test
    void getElevated() {
        assertEquals(elevatedUser, userService.getElevated(elevatedUser.getId()));
    }

    @Test
    void getStudentNotStudent() {
        assertNull(userService.getStudent(1));
        assertNull(userService.getStudent(elevatedUser.getId()));
    }

    @Test
    void  getStudent() {
        assertEquals(student, userService.getStudent(student.getId()));
    }



}
