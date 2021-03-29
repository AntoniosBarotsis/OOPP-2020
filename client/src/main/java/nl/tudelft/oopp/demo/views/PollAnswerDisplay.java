package nl.tudelft.oopp.demo.views;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.polls.AnswerPollController;

import java.io.IOException;
import java.util.Arrays;

public class PollAnswerDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
    // Initialize a loader for the main menu.
    FXMLLoader loader = new FXMLLoader();
    loader.setLocation(getClass().getResource("/pollView/pollAnswerView.fxml"));
    Parent root = loader.load();
    AnswerPollController controller = loader.getController();

    //Load data
    String a[] = new String[] {"A", "B", "A", "B", "A", "B", "A", "B"};
    controller.loadData(Arrays.asList(a), 1);

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
