package nl.tudelft.oopp.demo.controllers.startscreen;

import java.io.IOException;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import javafx.stage.Stage;

import nl.tudelft.oopp.demo.communication.startscreen.StartCommunication;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainModController;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainStudentController;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;



public class JoinController {

    @FXML
    private TextField inputRoomCode;
    @FXML
    private TextField inputUsername;
    @FXML
    private Button buttonJoinRoom;

    /**
     * Join button action (Button should join room eventually.
     */
    @FXML
    public void joinButton() throws IOException {
        if (inputRoomCode.getText().isEmpty() || inputUsername.getText().isEmpty()) {
            showAlert("Input username and room code, please!", Alert.AlertType.WARNING);
        }
        User user = StartCommunication.joinRoom(inputRoomCode.getText(),
                    inputUsername.getText());
        Room room = StartCommunication.getRoom(inputRoomCode.getText());

        // Check if room and user were successfully fetched.
        if (room == null || user == null || room.getId() == 0 || user.getId() == 0) {
            showAlert("Error joining a room. "
                    + "The room has not started yet or you have provided a wrong room code!",
                    Alert.AlertType.ERROR);
            return;
        }

        if (user.getUserType() == User.UserType.STUDENT) {
            startMainStudentMenu(room, user);
        } else {
            startMainModMenu(room, user);
        }
    }

    /**
     * The starting of a MainModMenu.
     * @param room the room that will be joined
     * @param user the user that is joining
     * @throws IOException from fxml loader
     */
    @FXML
    public void startMainModMenu(Room room, User user) throws IOException {
        // Initialize a loader for the main menu.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainmenu/mainModScene.fxml"));
        Parent root = loader.load();
        MainModController controller = loader.getController();

        // Inject the data.
        controller.loadData(room, user);

        // Load the Stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(room.getTitle());
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
        });
        stage.show();

        // Closing old Stage
        Stage oldStage = (Stage) inputUsername.getScene().getWindow();
        oldStage.close();
    }

    /**
     * the starting of a MainStudentMenu.
     * @param room the room that will be joined
     * @param user the user that is joining
     * @throws IOException from fxml loader
     */
    @FXML
    public void startMainStudentMenu(Room room, User user) throws IOException  {
        // Initialize a loader for the main menu.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainmenu/mainStudentScene.fxml"));
        Parent root = loader.load();
        MainStudentController controller = loader.getController();

        // Inject the data.
        controller.loadData(room, user);

        // Load the Stage
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle(room.getTitle());
        stage.setResizable(false);
        stage.show();

        // Closing old Stage
        Stage oldStage = (Stage) inputUsername.getScene().getWindow();
        oldStage.close();
    }

    /**
     * Button to go back to startScene.
     * @throws Exception fxml loader
     */
    @FXML
    public void buttonBack() throws Exception {
        Stage stage = (Stage) inputUsername.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/startScene.fxml"));
        Scene scene = new Scene(root, 960, 574);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Shows a warning alert on screen.
     * @param text text to display on alert
     * @param alertType the type of alert to display
     */
    protected void showAlert(String text, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
