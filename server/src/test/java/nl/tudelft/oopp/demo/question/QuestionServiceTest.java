package nl.tudelft.oopp.demo.question;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.entities.helpers.StudentHelper;
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

import java.io.UnsupportedEncodingException;

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
        assertEquals("This is the text 1", questionService.getText(id1));
    }

    @Test
    void setTextV1() {
        try {
            questionService.setText(id1, "this question has changed");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        assertEquals("this question has changed", questionService.getText(id1));
    }
    @Test
    void setTextV2() {
        StudentHelper studentHelper = new StudentHelper("UserName 1", "IP 1");
        QuestionHelper questionHelper= new QuestionHelper("this question has changed", studentHelper);

        questionService.setText(question1.getId(), questionHelper);

        assertEquals("this question has changed", questionService.getText(id1));
    }

    @Test
    void getAuthor() {

        assertEquals(user1, questionService.getAuthor(id1));
    }

    @Test
    void upvote() {
        assertEquals(0, questionService.getUpvotes(id1));
        questionService.upvote(id1);
        assertEquals(1, questionService.getUpvotes(id1));

    }

    @Test
    void downvoteStaysZero() {
        assertEquals(0, questionService.getUpvotes(id1));
        questionService.downvote(id1);
        assertEquals(0, questionService.getUpvotes(id1));
    }

    @Test
    void downvoteStaysDecreases() {
        assertEquals(0, questionService.getUpvotes(id1));
        questionService.upvote(id1);
        assertEquals(1, questionService.getUpvotes(id1));
        questionService.downvote(id1);
        assertEquals(0, questionService.getUpvotes(id1));
    }

    @Test
    void getUpvotes() {
        assertEquals(0, questionService.getUpvotes(id1));
    }

    @Test
    void getScore() {
        assertEquals(0, questionService.getScore(id1));
    }

    @Test
    void setScore() {
        questionService.setScore(id1, 10);
        assertEquals(10, questionService.getScore(id1));
    }

    @Test
    void setScoreNegative() {
        questionService.setScore(id1, -1);
        assertEquals(0, questionService.getScore(id1));
    }


    @Test
    void getHighest() {
        questionService.setScore(id3, 2);
        assertEquals(id3, questionService.getHighest(0));
    }

    @Test
    void getDate() {
        assertEquals(question1.getTimeCreated(), questionService.getDate(id1));
    }

    @Test
    void getStatus() {
        assertEquals(question1.getStatus(), questionService.getStatus(id1));

    }

    @Test
    void setAnswered() {
        questionService.setAnswered(id1);
        assertEquals(Question.QuestionStatus.ANSWERED, questionService.getStatus(id1));
    }

    @Test
    void userSetAnsweredLessThan5() {
        questionService.userSetAnswered(id1);
        assertEquals(Question.QuestionStatus.ANSWERED, questionService.getStatus(id1));
    }

    @Test
    void userSetAnsweredGreaterThan5() {
        questionService.setScore(id1, 6);
        questionService.userSetAnswered(id1);
        assertEquals(Question.QuestionStatus.OPEN, questionService.getStatus(id1));
    }

    @Test
    void userSetAnsweredGreaterThanX() {
        questionService.setScore(id1, 11);
        questionService.userSetAnswered(id1, 10);
        assertEquals(Question.QuestionStatus.OPEN, questionService.getStatus(id1));

    }

    @Test
    void userSetAnsweredLessThanX() {
        questionService.setScore(id1, 9);
        questionService.userSetAnswered(id1, 10);
        assertEquals(Question.QuestionStatus.ANSWERED, questionService.getStatus(id1));
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