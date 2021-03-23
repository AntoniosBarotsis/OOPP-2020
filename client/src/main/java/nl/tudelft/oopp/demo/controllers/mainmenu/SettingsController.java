package nl.tudelft.oopp.demo.controllers.mainmenu;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
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

        // Load all the data in the textBoxes.
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
        int studentRefresh = 0;
        int modRefresh = 0;
        int questionCooldown = 0;
        int paceCooldown = 0;
        try {
            // Fetch the data from the textBoxes.
            studentRefresh = Integer.parseInt(tbStudentrefresh.getText());
            modRefresh = Integer.parseInt(tbModRefresh.getText());
            questionCooldown = Integer.parseInt(tbQuestionCd.getText());
            paceCooldown = Integer.parseInt(tbPaceCd.getText());

            // Validate the data.
            if (studentRefresh < 1) {
                showAlert("Refresh rate for students must be at least 1 second.");
                return;
            }

            if (modRefresh < 1) {
                showAlert("Refresh rate for moderators must be at least 1 second.");
                return;
            }

            if (questionCooldown < 1 || questionCooldown > 599) {
                showAlert("Cooldown for asking questions must be between 1 second and 599 seconds.");
                return;
            }

            if (paceCooldown < 1 || paceCooldown > 599) {
                showAlert("Cooldown for setting the pace must be between 1 second and 599 seconds.");
                return;
            }

        } catch (NumberFormatException e) {
            showAlert("Textboxes must contain only integer data!");
        }
    }

    /**
     * Shows a warning alert on screen.
     * @param text text to display on alert
     */
    protected void showAlert(String text){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setHeaderText(null);
        alert.setContentText(text);
        alert.showAndWait();
    }
}
