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

    @Test
    void loadData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the client application.
        2. Join a room.
        3. Upon joining tooFast and tooSlow labels should be successfully set.
        4. The questions for the chosen room should appear as a list in the centre of the window.
         */
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
    void buttonMakePollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonShowPollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonAnsweredClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if button Answered Questions works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing the button it's text should change to "Unanswered questions".
        4. Only answered questions should be displayed in the listView.
        5. If you press again, unanswered questions should appear and button text should change.
         */
    }
}