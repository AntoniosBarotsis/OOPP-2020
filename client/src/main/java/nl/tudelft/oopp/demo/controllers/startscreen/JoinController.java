package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

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
    public void joinButton() {
        System.out.println("Join works!");
    }
}
