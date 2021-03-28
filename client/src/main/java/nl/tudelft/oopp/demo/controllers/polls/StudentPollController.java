package nl.tudelft.oopp.demo.controllers.polls;

import javafx.fxml.FXML;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class StudentPollController extends PollController {

    /**
     * Initialises a new Poll view for students.
     * @param poll current poll to display
     * @param user current user object
     * @param room current room object
     */
    public void loadData(Poll poll, User user, Room room) {
        super.loadData(poll, user, room);

        // Options button should be visible only if the poll is open or if statistics are shown.
        buttonOptions.setVisible(!poll.getStatus().equals(Poll.PollStatus.CLOSED));
    }

    /**
     * Handles button "Options" clicks.
     */
    @FXML
    public void buttonOptionsClicked() {
        // TODO: button should launch a new window for Students
    }
}
