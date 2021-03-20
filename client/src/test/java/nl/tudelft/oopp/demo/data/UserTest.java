package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import org.junit.jupiter.api.Test;

class UserTest {

    private Set<Long> set = new HashSet<>();
    private User user = new User(1234, "teachingAssistant1234", set, set, User.UserType.MODERATOR);

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
        set.add(1L);
        assertEquals(set, user.getQuestionsAsked());
    }

    @Test
    void getQuestionsUpvoted() {
        assertEquals(set, user.getQuestionsAsked());
    }
}