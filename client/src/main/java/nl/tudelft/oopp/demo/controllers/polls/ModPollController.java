package nl.tudelft.oopp.demo.controllers.polls;

import javafx.fxml.FXML;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class ModPollController extends PollController {

    /**
     * Initialises a new Poll view for moderators.
     * @param poll current poll to display
     * @param user current user object
     * @param room current room object
     */
    public void loadData(Poll poll, User user, Room room) {


    }

    /**
     * Handles button "Options" clicks.
     */
    @FXML
    public void buttonOptionsClicked() {
        // TODO: button should launch a new window for Moderators
    }
}
