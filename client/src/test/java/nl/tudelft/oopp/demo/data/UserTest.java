package nl.tudelft.oopp.demo.data;

import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Queue;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class UserTest {

    private Set<Question> set = new HashSet<>();
    private User user = new User(1234, User.UserType.MODERATOR, "teachingAssistant1234", set, set);
    private Question question = new Question(1, "question", user, 0, 0, new Date(1234567890), Question.QuestionStatus.OPEN, "");

    @Test
    void getId() {
        assertEquals(1234, user.getId());
    }

    @Test
    void setId() {
        user.setId(1235);
        assertEquals(1235, user.getId());
    }

    @Test
    void getUserType() {
        assertEquals(User.UserType.MODERATOR, user.getUserType());
    }

    @Test
    void setUserType() {
        user.setUserType(User.UserType.LECTURER);
        assertEquals(User.UserType.LECTURER, user.getUserType());
    }

    @Test
    void getUsername() {
        assertEquals("teachingAssistant1234", user.getUsername());
    }

    @Test
    void setUsername() {
        user.setUsername("teachingAssistant1235");
        assertEquals("teachingAssistant1235", user.getUsername());
    }

    @Test
    void getQuestionsAsked() {
        set.add(question);
        assertEquals(set, user.getQuestionsAsked());
    }

    @Test
    void getQuestionsUpvoted() {
        assertEquals(set, user.getQuestionsAsked());
    }
}