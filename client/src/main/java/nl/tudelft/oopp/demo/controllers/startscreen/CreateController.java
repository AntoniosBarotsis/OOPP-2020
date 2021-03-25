package nl.tudelft.oopp.demo.controllers.startscreen;

import java.io.IOException;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
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
            //TODO: Replace this with a error popup
            roomName = "Room";
        }
        String username = inputUsername.getText();
        if (username.equals("")) {
            //TODO: Replace this with a error popup
            username = "Admin";
        }
        if (!inputSchedule.isSelected()) {
            room = StartCommunication.createRoom(roomName, username);
        } else {
            //TODO: Insert a error popup when empty
            String date = inputDate.toString();
            String time = inputTime.getText();

            // Since Date is a dick, this is how you make it :)
            Calendar fullDate = new GregorianCalendar(Integer.parseInt(
                    (date.substring(0, 3))) + 1900,
                    Integer.parseInt(date.substring(5, 6)),
                    Integer.parseInt(date.substring(7, 8)),
                    Integer.parseInt(time.substring(0, 1)),
                    Integer.parseInt(time.substring(2, 3)));
            Date actualDate = fullDate.getTime();
            room = StartCommunication.createScheduledRoom(roomName, username, actualDate);
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
    }

}
