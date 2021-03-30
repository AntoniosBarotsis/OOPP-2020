package nl.tudelft.oopp.demo.controllers.polls;

import java.io.IOException;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
    public void buttonOptionsClicked() throws IOException {
        // Initialize a loader.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pollView/pollAnswerView.fxml"));
        Parent root = loader.load();
        AnswerPollController controller = loader.getController();

        // Inject the data.
        // controller.loadData(poll.getOptions(), 0);

        // Assign options to loader.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
