package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.text.Text;

public class CreateController {

    @FXML
    private TextField inputRoomName;
    @FXML
    private DatePicker inputDate;
    @FXML
    private TextField inputTime;
    @FXML
    private Text textCodeStudent;
    @FXML
    private Text textCodeMod;
    @FXML
    private Button buttonCreateRoom;

    /**
     * Create button action (Should create room eventually).
     */

    @FXML
    public void createButton() {
        System.out.println("Create works!");
    }

    /**
     * Actually shows the codes.
     * @param studentCode String containing the code for students
     * @param moderatorCode String containing the code for moderators
     */
    public void roomCodes(String studentCode, String moderatorCode) {
        textCodeStudent.setText("Code for students: " + studentCode);
        textCodeMod.setText("Code for moderators: " + moderatorCode);
    }
}
