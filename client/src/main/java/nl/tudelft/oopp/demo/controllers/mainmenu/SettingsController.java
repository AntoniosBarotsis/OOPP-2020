package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.util.Optional;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import nl.tudelft.oopp.demo.communication.mainmenu.SettingsCommunication;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;


public class SettingsController {

    private Room room;
    private User user;

    @FXML
    Button buttonDelete;
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
        tbStudentrefresh.setText(String.valueOf(room.getSettings().getStudentRefreshRate()));
        tbModRefresh.setText(String.valueOf(room.getSettings().getModRefreshRate()));
        tbQuestionCd.setText(String.valueOf(room.getSettings().getQuestionCooldown()));
        tbPaceCd.setText(String.valueOf(room.getSettings().getPaceCooldown()));

        if (!user.getUserType().equals(User.UserType.LECTURER)) {
            buttonDelete.setVisible(false);
        }
    }

    /**
     * Validates the input data and saves it on server.
     */
    @FXML
    public void buttonSaveClicked() {
        try {
            // Validate student refresh rate.
            int studentRefresh = Integer.parseInt(tbStudentrefresh.getText());
            if (studentRefresh < 1) {
                showAlert("Refresh rate for students must be at least 1 second.",
                        Alert.AlertType.WARNING);
                return;
            }

            // Validate mod refresh rate.
            int modRefresh = Integer.parseInt(tbModRefresh.getText());
            if (modRefresh < 1) {
                showAlert("Refresh rate for moderators must be at least 1 second.",
                        Alert.AlertType.WARNING);
                return;
            }

            // Validate question cooldown.
            int questionCooldown = Integer.parseInt(tbQuestionCd.getText());
            if (questionCooldown < 2 || questionCooldown > 600) {
                showAlert("Cooldown for asking questions must be "
                        + "between 2 seconds and 600 seconds.", Alert.AlertType.WARNING);
                return;
            }

            // Validate pace cooldown.
            int paceCooldown = Integer.parseInt(tbPaceCd.getText());
            if (paceCooldown < 2 || paceCooldown > 600) {
                showAlert("Cooldown for setting the pace must be "
                        + "between 2 seconds and 600 seconds.", Alert.AlertType.WARNING);
                return;
            }

            // Save the changes to server.
            RoomConfig roomConfig = new RoomConfig(studentRefresh, modRefresh,
                    questionCooldown, paceCooldown);
            String response = SettingsCommunication.saveSettings(room.getId(),
                    user.getId(), roomConfig);
            room.setSettings(roomConfig);

            if (response.equals("200")) {
                showAlert("Settings saved!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error saving settings!", Alert.AlertType.ERROR);
            }


        } catch (NumberFormatException e) {
            showAlert("Textboxes must contain only integer data!", Alert.AlertType.WARNING);
        }
    }

    /**
     * Deletes all questions in a room.
     */
    @FXML
    public void buttonDeleteClicked() {
        Optional<ButtonType> result = showAlert(
                "Are you sure you want to delete all questions in a room?",
                Alert.AlertType.CONFIRMATION);
        if (result.isPresent() && result.get() == ButtonType.OK) {
            String response = SettingsCommunication
                    .deleteQuestions(room.getId(), user.getId());
            if (response.equals("200")) {
                showAlert("Questions deleted!", Alert.AlertType.INFORMATION);
            } else {
                showAlert("Error deleting questions!", Alert.AlertType.ERROR);
            }

        }
    }

    /**
     * Shows a warning alert on screen.
     * @param text text to display on alert
     * @param alertType the type of alert to display
     * @return alert response
     */
    protected Optional<ButtonType> showAlert(String text, Alert.AlertType alertType) {
        Alert alert = new Alert(alertType);
        alert.setHeaderText(null);
        alert.setContentText(text);
        return alert.showAndWait();
    }
}
