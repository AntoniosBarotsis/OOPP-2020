package nl.tudelft.oopp.demo.controllers.polls;

import java.io.IOException;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
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
        super.loadData(poll, user, room);
    }

    /**
     * Handles button "Options" clicks.
     */
    public void buttonOptionsClicked() throws IOException {
        // Initialize a loader.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pollView/pollModAskView.fxml"));
        Parent root = loader.load();
        ModAskPollController controller = loader.getController();

        // Inject the data.
        controller.loadData(poll, user, room);

        // Assign options to loader.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }
}
