package nl.tudelft.oopp.demo.question;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidIdException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.repositories.*;
import nl.tudelft.oopp.demo.services.QuestionService;
import nl.tudelft.oopp.demo.services.RoomService;
import nl.tudelft.oopp.demo.services.UserService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import javax.persistence.Entity;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
class QuestionServiceTest {

    @Autowired
    private QuestionRepository questionRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private LogEntryRepository logEntryRepository;

    @Autowired
    private RoomConfigRepository roomConfigRepository;


    private UserService userService;

    private QuestionService questionService;

    private RoomService roomService;



    private long id1;
    private long id2;
    private long id3;

    private User user1;
    private User user2;
    private User user3;
    private ElevatedUser admin;


    private Question question1;
    private Question question2;
    private Question question3;
    private Question question4;

    private Room room;



    @BeforeEach
    void setUp() {
        userService = new UserService(userRepository);
        questionService = new QuestionService(questionRepository, userRepository, roomRepository, logEntryRepository, userService);
        roomService = new RoomService(roomRepository, userRepository, logEntryRepository, roomConfigRepository, userService);

        user1 = new Student("UserName 1", "IP 1");
        user2 = new Student("UserName 2", "IP 2");
        user3 = new Student("UserName 3", "ajnkdsjnasdjnascjnk");

        admin = new ElevatedUser("admin", "IP 4", true);

        question1 = new Question("This is the text 1", user1);
        question2 = new Question("This is the text 2", user1);
        question3 = new Question("This is the text 3", user2);
        question4 = new Question("This is the text 4", user3);

        room = roomService.createRoom("admin", "IP 4", "roomName");

        userRepository.save(user1);
        userRepository.save(user2);

        questionRepository.save(question1);
        questionRepository.save(question2);
        questionRepository.save(question3);

        id1 = question1.getId();
        id2 = question2.getId();
        id3 = question3.getId();



    }

    @Test
    void addQuestionInvalidUser() {
        Assertions.assertThrows(InvalidIdException.class, () -> {
            questionService.addQuestion(question4, room.getId());
        });
    }

    @Test
    void addQuestion() {
        userRepository.save(user3);
//        roomRepository.banUser(roomRepository.getNormalRoomId(room.getElevatedPassword()), "IP 3");
        Assertions.assertDoesNotThrow(() -> {
            questionService.addQuestion(question4, room.getId());
        });
    }

    @Test
    void addQuestionUnauthorisedUser() {
//        userRepository.save(user3);
//        roomService.banUser(room.getId(),user3.getId(), user3.getIp(), room.getElevatedPassword());
//        Assertions.assertThrows(UnauthorizedException.class, () -> {
//            questionService.addQuestion(question4, room.getId());
//        });
    }

    @Test
    void getQuestion() {
        assertEquals(questionService.getQuestion(question1.getId()), question1);
    }

    @Test
    void getText() {
    }

    @Test
    void setText() {
    }

    @Test
    void testSetText() {
    }

    @Test
    void getAuthor() {
    }

    @Test
    void upvote() {
    }

    @Test
    void downvote() {
    }

    @Test
    void getUpvotes() {
    }

    @Test
    void getScore() {
    }

    @Test
    void setScore() {
    }

    @Test
    void getHighest() {
    }

    @Test
    void getDate() {
    }

    @Test
    void getStatus() {
    }

    @Test
    void setAnswered() {
    }

    @Test
    void userSetAnswered() {
    }

    @Test
    void testUserSetAnswered() {
    }

    @Test
    void setSpam() {
    }

    @Test
    void setOpen() {
    }

    @Test
    void getAnswer() {
    }

    @Test
    void setAnswer() {
    }

    @Test
    void testSetAnswer() {
    }

    @Test
    void deleteOneQuestion() {
    }

    @Test
    void deleteAllQuestions() {
    }

    @Test
    void export() {
    }

    @Test
    void exportAll() {
    }

    @Test
    void exportTop() {
    }

    @Test
    void exportAnswered() {
    }

    @Test
    void mapQuestionExport() {
    }
}