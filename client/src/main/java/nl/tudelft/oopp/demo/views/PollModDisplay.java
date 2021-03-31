package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.polls.ModAskPollController;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;

public class PollModDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize a loader for the main menu.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pollView/pollModAskView.fxml"));
        Parent root = loader.load();
        ModAskPollController controller = loader.getController();

        //Load data
        List<String> list = Arrays.asList("", "", "answer1", "answer2", "answer3", "", "",
                "", "answer4", "");
        List<String> listAnswers = Arrays.asList("", "", "answer1");

        Poll poll = new Poll(1, "text", new Date(), list,
                listAnswers, Poll.PollStatus.CLOSED);
        RoomConfig settings = new RoomConfig(5, 5, 300, 300);

        Room room = new Room(1, "room", new Date(), false, 0,0,
                0, true, settings);
        User user = new User(0, "Roy", null, null, User.UserType.LECTURER);
        controller.loadData(poll, user, room);

        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
}
