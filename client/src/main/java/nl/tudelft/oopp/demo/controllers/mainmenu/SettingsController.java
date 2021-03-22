package nl.tudelft.oopp.demo.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.demo.communication.mainmenu.SettingsCommunication;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class SettingsController {

    private Room room;
    private User user;

    @FXML
    TextArea tbCodes;
    @FXML
    TextField tbStudentrefresh;
    @FXML
    TextField tbModRefresh;
    @FXML
    TextField tbQuestionCd;
    @FXML
    TextField tbPaceCd;

    /**
     * Injects data from previous scene into current Settings.
     * @param room current room
     * @param user current user
     */
    public void loadData(Room room, User user) {
        this.room = room;
        this.user = user;

        // Load all the data
        String studentCode = "Code for students: "
                + SettingsCommunication.getStudentPassword(room.getId()) + "\n";
        String moderatorCode = "Code for moderators: "
                + SettingsCommunication.getAdminPassword(room.getId()) + "\n";
        tbCodes.setText(studentCode + moderatorCode);
        tbStudentrefresh.setText(String.valueOf(1));
        tbModRefresh.setText(String.valueOf(1));
        tbQuestionCd.setText(String.valueOf(1));
        tbPaceCd.setText(String.valueOf(1));
    }

    /**
     * Validates the input data and saves it on server.
     */
    @FXML
    public void buttonSaveClicked() {
        // TODO: implement validation and save data to server.
    }
}
