package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class QuestionTest {

    private Set<Long> set = new HashSet<>();
    private Question question = new Question(1, "question", 1L, 0, 0,
        new Date(1234567890), Question.QuestionStatus.OPEN, "");

    @Test
    void getId() {
        assertEquals(1, question.getId());
    }

    @Test
    void setId() {
        question.setId(2);
        assertEquals(2, question.getId());
    }

    @Test
    void getText() {
        assertEquals("question", question.getText());
    }

    @Test
    void setText() {
        question.setText("question text here");
        assertEquals("question text here", question.getText());
    }

    @Test
    void getAuthor() {
        assertEquals(1L, question.getAuthor());
    }

    @Test
    void setAuthor() {
        User user2 = new User(1236, "student1236", set, set, User.UserType.STUDENT);
        question.setAuthor(1236L);
        assertEquals(user2.getId(), question.getAuthor());
    }

    @Test
    void getUpvotes() {
        assertEquals(0, question.getUpvotes());
    }

    @Test
    void setUpvotes() {
        question.setUpvotes(1);
        assertEquals(1, question.getUpvotes());
    }

    @Test
    void getScore() {
        assertEquals(0, question.getScore());
    }

    @Test
    void setScore() {
        question.setScore(1);
        assertEquals(1, question.getScore());
    }

    @Test
    void getTimeCreated() {
        assertEquals(new Date(1234567890), question.getTimeCreated());
    }

    @Test
    void setTimeCreated() {
        question.setTimeCreated(new Date(1234567810));
        assertEquals(new Date(1234567810), question.getTimeCreated());
    }

    @Test
    void getStatus() {
        assertEquals(Question.QuestionStatus.OPEN, question.getStatus());
    }

    @Test
    void setStatus() {
        question.setStatus(Question.QuestionStatus.ANSWERED);
        assertEquals(Question.QuestionStatus.ANSWERED, question.getStatus());
    }

    @Test
    void getAnswer() {
        assertEquals("", question.getAnswer());
    }

    @Test
    void setAnswer() {
        question.setAnswer("question answer here");
        assertEquals("question answer here", question.getAnswer());
    }
}