package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.util.Arrays;

import javafx.application.Application;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import nl.tudelft.oopp.demo.controllers.polls.AnswerPollController;


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
        String[] a = new String[]{"A", "B", "A", "B", "A", "B", "A", "B"};
        controller.loadView(Arrays.asList(a));

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
