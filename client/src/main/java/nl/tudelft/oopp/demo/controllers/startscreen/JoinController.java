package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

import nl.tudelft.oopp.demo.communication.startscreen.StartCommunication;
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
    public void joinButton() {
        if (!inputRoomCode.getText().isEmpty() && !inputUsername.getText().isEmpty()) {
            User user = StartCommunication.joinRoom(inputRoomCode.getText(),
                    inputUsername.getText());
        }
    }
}
