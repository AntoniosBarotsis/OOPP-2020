package nl.tudelft.oopp.demo.data.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;

import com.google.gson.Gson;
import nl.tudelft.oopp.demo.data.User;
import org.junit.jupiter.api.Test;

import java.util.Set;

class UserInstanceCreatorTest {

    private static Gson gson = new Gson();

    @Test
    void testDeserialization() {
        String json = "{\"id\":5120336323440220676,\"username\":\"Admin\","
                + "\"userType\":\"LECTURER\",\"questionsAsked\":[1, 2],"
                + "\"questionsUpvoted\":[1]}";

        User user = gson.fromJson(json, User.class);
        assertEquals(5120336323440220676L, user.getId());
        assertEquals("Admin", user.getUsername());
        assertEquals(User.UserType.LECTURER, user.getUserType());
        assertEquals(Set.of(1L, 2L), user.getQuestionsAsked());
        assertEquals(Set.of(1L), user.getQuestionsUpvoted());
    }
}