package nl.tudelft.oopp.demo.controllers.mainmenu;

import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

class MainModControllerTest {

    private Set<Question> set = new HashSet<>();
    private MainModController controller = new MainModController();
    private User user = new User(1, User.UserType.MODERATOR, "teachingAssistant1234", set, set);
    private Room room = new Room(1, "OOPP lecture 1", new Date(1614969845), true, 5, 1);

    @Test
    void loadData() {
        controller.loadData(room, user);
        assertNotNull(controller.getRoom());
        assertNotNull(controller.getUser());
    }

    @Test
    void getRoom() {
        controller.loadData(room, user);
        assertEquals(room, controller.getRoom());
    }

    @Test
    void getUser() {
        controller.loadData(room, user);
        assertEquals(user, controller.getUser());
    }

    @Test
    void buttonLinksClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonLinks works:
        1. Launch the client application.
        2. Join a room.
        3. Click on top-right button "Links".
        4. An informational dialog should appear, which shows code for students and moderators.
         */
    }

    @Test
    void buttonExportClicked() {
        //To be implemented.
    }

    @Test
    void testLoadData() {
        //To be implemented.
    }

    @Test
    void buttonMakePollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonShowPollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonAnsweredClicked() {
        //To be implemented.
    }
}