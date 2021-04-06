package nl.tudelft.oopp.demo.controllers.startscreen;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Date;

import javafx.application.Platform;
import javafx.fxml.FXML;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.CheckBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;

import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.mainmenu.SettingsCommunication;
import nl.tudelft.oopp.demo.communication.startscreen.StartCommunication;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainModController;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.RoomHelper;

public class CreateController {

    @FXML
    private TextField inputRoomName;
    @FXML
    private TextField inputUsername;
    @FXML
    private CheckBox inputSchedule;
    @FXML
    private CheckBox inputRepeating;
    @FXML
    private DatePicker inputDate;
    @FXML
    private TextField inputTime;
    @FXML
    private TextField inputTimeEnd;
    @FXML
    private TextField tbStudentrefresh;
    @FXML
    private TextField tbModRefresh;
    @FXML
    private TextField tbQuestionCd;
    @FXML
    private TextField tbPaceCd;

    /**
     * Checks what type of room is being created and calls correct method.
     * If room name is blank, it will be replaced with "Room"
     * If username is blank, it will be replaced with "Admin"
     */
    @FXML
    public void createButton() throws IOException {
        String roomName = inputRoomName.getText();
        if (roomName.equals("")) {
            showAlert("Insert a room name, please.", Alert.AlertType.ERROR);
            return;
        }
        String username = inputUsername.getText();
        if (username.equals("")) {
            showAlert("Insert your username, please.", Alert.AlertType.ERROR);
            return;
        }

        // Fetch room configurations.
        RoomConfig roomConfig = validatePaceSetting();
        if (roomConfig == null) {
            return;
        }

        // Try to create a room.
        Room room = null;
        room = createRoom(roomConfig, roomName, username);

        // Check if a room was fetched from server.
        if (room == null) {
            showAlert("Error creating a room.", Alert.AlertType.ERROR);
            return;
        }

        // Join a room.
        String password = StartCommunication.getPrivateCode(room.getId());
        User user = StartCommunication.joinRoom(password, username);
        startMainModMenu(room, user);
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

    /**
     * Creates and fetches new room from server.
     * @param roomConfig room configuration
     * @param roomName name of room
     * @param username name of admin
     * @return fetched room from server
     */
    protected Room createRoom(RoomConfig roomConfig, String roomName, String username) {
        try {
            //Fetch date.
            String startDate = inputDate.getValue() == null ? "" : inputDate.getValue().toString();
            String startTime = inputTime.getText();
            String endDate = inputDate.getValue() == null ? "" : inputDate.getValue().toString();
            String endTime = inputTimeEnd.getText();

            DateTimeFormatter pattern = DateTimeFormatter.ofPattern("yyyy-MM-dd hh:mm:ss");
            String start = LocalDateTime.now().format(pattern);
            String end = LocalDateTime.now().format(pattern);

            if (inputSchedule.isSelected()) {
                start = startDate + " " + startTime + ":00";
                end = endDate + " " + endTime + ":00";

            }

            Date checkStart = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(start);
            Date checkEnd = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(end);

            return StartCommunication.createRoom(new RoomHelper(roomName, username,
                    inputRepeating.isSelected(), roomConfig, start, end));
        } catch (ParseException e) {
            e.printStackTrace();
            showAlert("Error parsing date and time.", Alert.AlertType.ERROR);
            return null;
        }
    }

    /**
     * Validates room configurations are set correctly.
     * @return configurations of new room
     */
    protected RoomConfig validatePaceSetting() {
        try {
            // Validate student refresh rate.
            int studentRefresh = Integer.parseInt(tbStudentrefresh.getText());
            if (studentRefresh < 1) {
                showAlert("Refresh rate for students must be at least 1 second.",
                        Alert.AlertType.WARNING);
                return null;
            }

            // Validate mod refresh rate.
            int modRefresh = Integer.parseInt(tbModRefresh.getText());
            if (modRefresh < 1) {
                showAlert("Refresh rate for moderators must be at least 1 second.",
                        Alert.AlertType.WARNING);
                return null;
            }

            // Validate question cooldown.
            int questionCooldown = Integer.parseInt(tbQuestionCd.getText());
            if (questionCooldown < 2 || questionCooldown > 600) {
                showAlert("Cooldown for asking questions must be "
                        + "between 2 seconds and 600 seconds.", Alert.AlertType.WARNING);
                return null;
            }

            // Validate pace cooldown.
            int paceCooldown = Integer.parseInt(tbPaceCd.getText());
            if (paceCooldown < 2 || paceCooldown > 600) {
                showAlert("Cooldown for setting the pace must be "
                        + "between 2 seconds and 600 seconds.", Alert.AlertType.WARNING);
                return null;
            }

            return new RoomConfig(studentRefresh, modRefresh,
                    questionCooldown, paceCooldown);


        } catch (NumberFormatException e) {
            showAlert("Textboxes must contain only integer data!", Alert.AlertType.WARNING);
            return null;
        }
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
        stage.setTitle(room.getTitle());
        stage.setResizable(false);
        stage.setOnCloseRequest(e -> {
            Platform.exit();
        });
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
        Scene scene = new Scene(root, 960, 574);
        stage.setScene(scene);
        stage.show();
    }

}
