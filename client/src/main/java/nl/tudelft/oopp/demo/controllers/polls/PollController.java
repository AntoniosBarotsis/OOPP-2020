package nl.tudelft.oopp.demo.controllers.polls;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public abstract class PollController {

    protected Poll poll;
    protected User user;
    protected Room room;
    @FXML
    private Label labelTimeCreated;
    @FXML
    private Label labelStatus;
    @FXML
    private Label labelParticipants;
    @FXML
    private Label labelText;
    @FXML
    protected Button buttonOptions;

    /**
     * Initialises a new Poll view for moderators.
     * @param poll current poll to display
     * @param user current user object
     * @param room current room object
     */
    public void loadData(Poll poll, User user, Room room) {
        this.poll = poll;
        this.user = user;
        this.room = room;

        String simplifiedDate  = new SimpleDateFormat("HH:mm").format(poll.getTimeCreated());
        labelText.setText(poll.getText());
        labelTimeCreated.setText(simplifiedDate);
        labelStatus.setText("Status: " + poll.getStatus().toString());
    }

    /**
     * Handles button "Options" clicks.
     */
    @FXML
    public abstract void buttonOptionsClicked() throws IOException;
}
