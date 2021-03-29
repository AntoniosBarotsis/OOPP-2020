package nl.tudelft.oopp.demo.controllers.startscreen;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.startscreen.StartCommunication;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainModController;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class CreateController {

    @FXML
    private TextField inputRoomName;
    @FXML
    private TextField inputUsername;
    @FXML
    private CheckBox inputSchedule;
    @FXML
    private DatePicker inputDate;
    @FXML
    private TextField inputTime;

    /**
     * Checks what type of room is being created and calls correct method.
     * If room name is blank, it will be replaced with "Room"
     * If username is blank, it will be replaced with "Admin"
     */
    @FXML
    public void createButton() throws IOException {
        String roomName = inputRoomName.getText();
        Room room = null;
        if (roomName.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please insert a roomname!");
            alert.showAndWait();
        }
        String username = inputUsername.getText();
        if (username.equals("")) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Please insert a username");
            alert.showAndWait();
        }
        if (!inputSchedule.isSelected()) {
            room = StartCommunication.createRoom(roomName, username);
        } else {

            String date = inputDate.getValue().toString();
            String time = inputTime.getText();

            try {
                date = date + " " + time + ":00";
                room = StartCommunication.createScheduledRoom(roomName, username,
                        new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(date));
            } catch (ParseException e) {
                e.printStackTrace();
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText(null);
                alert.setContentText("Error parsing date and time.");
                alert.showAndWait();
            }
        }

        // Getting all the necessary stuff to join a room :D
        Long roomId = room.getId();
        String password = StartCommunication.getPrivateCode(roomId);
        User user = StartCommunication.joinRoom(password, username);
        startMainModMenu(room, user);
    }

    /**
     * Starting the MainModMenu.
     * @param room room that will be joined
     * @param user user that is joining the room
     * @throws IOException When fxml loader fails
     */
    @FXML
    public void startMainModMenu(Room room, User user) throws IOException  {
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
        stage.setTitle("Main menu");
        stage.setResizable(false);
        stage.show();

        // Close the previous window
        Stage oldStage = (Stage) inputSchedule.getScene().getWindow();
        oldStage.close();
    }

    /**
     * Button to go back to startScene.
     * @throws Exception fxml loader
     */
    @FXML
    public void buttonBack() throws Exception {
        Stage stage = (Stage) inputSchedule.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/startScene.fxml"));
        Scene scene = new Scene(root, 960, 540);
        stage.setScene(scene);
        stage.show();
    }

}
