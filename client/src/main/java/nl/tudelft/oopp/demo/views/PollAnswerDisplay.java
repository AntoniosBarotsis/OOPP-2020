package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import nl.tudelft.oopp.demo.controllers.polls.AnswerPollController;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;


public class PollAnswerDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Initialize a loader for the main menu.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/pollView/pollAnswerView.fxml"));
        Parent root = loader.load();
        AnswerPollController controller = loader.getController();

        //Load data
        //(Add more strings to see dynamic expansion (Max 10 strings)
        String[] a = new String[]{"A", "B", "C", "D", "E", "F", "G", "H"};
        Poll poll = new Poll(2, "TestQuestion", new Date(), Arrays.asList(a),
                Arrays.asList("A"), Poll.PollStatus.OPEN);
        RoomConfig settings = new RoomConfig(5, 5, 300, 300);
        Room room = new Room(1, "room", new Date(), false, 0, 0,
                0, true, settings);
        User user = new User(4, "Roy", new HashSet<>(), new HashSet<>(),
                User.UserType.STUDENT);
        controller.loadData(poll, user, room);

        // Load the Stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("Main menu");
        stage.setResizable(false);
        stage.show();
    }

    public static void main(String[] args)  {
        launch(args);
    }
}
