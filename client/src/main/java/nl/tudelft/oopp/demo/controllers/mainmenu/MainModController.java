package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.util.List;

import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import nl.tudelft.oopp.demo.communication.mainmenu.MainModCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class MainModController {

    private Room room;
    private User user;
    @FXML
    private ListView<String> questionList;
    @FXML
    private Text labelSlow;
    @FXML
    private Text labelFast;

    /**
     * Injects data from previous scene into current MainMenu.
     * @param room current room
     * @param user current user
     */
    public void loadData(Room room, User user) {
        fetchData(room, user);
        // TODO: implement automatically to call fetchData every 5 seconds
    }

    /**
     * Fetches data from server and injects in FXML.
     * @param room current room
     * @param user current user
     */
    public void fetchData(Room room, User user){
        // Fetch room data from server.
        this.room = MainModCommunication.getRoom(room.getId());
        this.user = user;

        // Set pace labels using the data fetched from server.
        labelSlow.setText(String.valueOf(this.room.getTooSlow()));
        labelFast.setText(String.valueOf(this.room.getTooFast()));

        // Fetch questions from database and load them into the ListView.
        questionList.getItems().clear();
        List<Question> questionData = MainModCommunication.getQuestions(this.room.getId());
        for (Question question : questionData) {
            /*
            TODO: questionList should loaded with FMXL panels instead of string.
             */
            questionList.getItems().add(question.getText());
        }
    }

    /**
     * Handles button "Links" clicks.
     */
    @FXML
    public void buttonLinksClicked() {
        String studentCode = "Code for students: "
                + MainModCommunication.getStudentPassword(room.getId()) + "\n";
        String moderatorCode = "Code for moderators: "
                + MainModCommunication.getAdminPassword(room.getId()) + "\n";

        // Create custom alert with copy-pastable text.
        TextArea textArea = new TextArea(studentCode + moderatorCode);
        textArea.setEditable(false);
        textArea.setWrapText(true);
        GridPane gridPane = new GridPane();
        gridPane.setMaxWidth(Double.MAX_VALUE);
        gridPane.add(textArea, 0, 0);

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Room codes");
        alert.setHeaderText(null);
        alert.getDialogPane().setContent(gridPane);
        alert.showAndWait();
    }

    /**
     * Handles button "Export" clicks.
     */
    @FXML
    public void buttonExportClicked() {
        //TODO: Exports button should give all log data to be exported.
    }

    /**
     * Handles button "As a multiple choice" clicks.
     */
    @FXML
    public void buttonMakePollsClicked() {
        //TODO: The button should open new window to create polls.
    }

    /**
     * Handles button "Previous polls" clicks.
     */
    @FXML
    public void buttonShowPollsClicked() {
        //TODO: The button should show all previous polls in questionView
    }

    /**
     * Handles button "Answered questions" clicks.
     */
    @FXML
    public void buttonAnsweredClicked() {
        //TODO: The button should show all answered questions in questionView
    }
}
