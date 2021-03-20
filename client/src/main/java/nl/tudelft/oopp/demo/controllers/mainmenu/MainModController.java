package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.util.Comparator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.GridPane;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
import javafx.util.Duration;

import nl.tudelft.oopp.demo.communication.mainmenu.MainModCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class MainModController {

    private boolean filterAnswered;
    private List<Question> questionData;
    private Room room;
    private User user;

    @FXML
    private ListView<String> questionList;
    @FXML
    private Text labelSlow;
    @FXML
    private Text labelFast;
    @FXML
    private Text labelNormal;
    @FXML
    private Button buttonAnswered;

    /**
     * Injects data from previous scene into current MainMenu.
     * @param room current room
     * @param user current user
     */
    public void loadData(Room room, User user) {
        filterAnswered = false;
        fetchData(room, user);

        // Automatically fetch data every 5 seconds.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> fetchData(this.room, this.user))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
    }

    /**
     * Fetches data from server and injects in FXML.
     * @param room current room
     * @param user current user
     */
    protected void fetchData(Room room, User user) {
        // Fetch room data from server.
        this.room = MainModCommunication.getRoom(room.getId());
        this.user = user;

        // Set pace labels using the data fetched from server.
        labelSlow.setText(String.valueOf(this.room.getTooSlow()));
        labelFast.setText(String.valueOf(this.room.getTooFast()));
        labelNormal.setText(String.valueOf(this.room.getNormalSpeed()));

        // Fetch questions from database and load them into the ListView.
        this.questionData = MainModCommunication.getQuestions(this.room.getId());

        // Sort questions by score.
        questionData.sort(Comparator.comparing(Question::getScore).reversed());

        // Populate the ListView with the fetched data.
        populateListView();
    }

    /**
     * Populates ListView with Questions data.
     */
    protected void populateListView() {
        questionList.getItems().clear();
        for (Question question : questionData) {
            /*
            TODO: questionList should be loaded with FXML panels instead of string.
             */
            boolean answered = question.getStatus().equals(Question.QuestionStatus.ANSWERED);
            if (!filterAnswered && !answered) {
                questionList.getItems().add(question.getText());
            } else if (filterAnswered && answered) {
                questionList.getItems().add(question.getText());
            }
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
        filterAnswered = !filterAnswered;
        populateListView();
        if (filterAnswered) {
            buttonAnswered.setText("Unanswered questions");
        } else {
            buttonAnswered.setText("Answered questions");
        }
    }

    /**
     * Handles MenuItem "Export all questions" clicks.
     */
    @FXML
    public void exportAllClicked() {
        String title = "all questions";
        String data = MainModCommunication.getAllQuestions(this.room.getId());
        directoryChooser(title, data);
    }

    /**
     * Handles MenuItem "Export top 20 questions" clicks.
     */
    @FXML
    public void exportTopClicked() {
        String title = "top questions";
        String data = MainModCommunication.getTopQuestions(this.room.getId());
        directoryChooser(title, data);
    }

    /**
     * Handles MenuItem "Export answered questions" clicks.
     */
    @FXML
    public void exportAnsweredClicked() {
        String title = "answered questions";
        String data = MainModCommunication.getAnsweredQuestions(this.room.getId());
        directoryChooser(title, data);
    }

    /**
     * Handles MenuItem "Export room log" clicks.
     */
    @FXML
    public void exportLogClicked() {
        //TODO: The MenuItem should export the room log.
    }

    /**
     * Create and save file containing exported data.
     * @param title title of FileChooser window
     * @param data data to be exported
     */
    public void directoryChooser(String title, String data) {
        // Initialize fileChooser.
        FileChooser chooser = new FileChooser();
        chooser.setTitle("Export " + title);
        FileChooser.ExtensionFilter extFilter =
                new FileChooser.ExtensionFilter("TXT files (*.txt)", "*.txt");
        chooser.getExtensionFilters().add(extFilter);

        File selectedFile = null;

        // Choose file name and location.
        selectedFile = chooser.showSaveDialog(null);

        // Return if no file was chosen.
        if (selectedFile == null) {
            return;
        }

        // Write the data.
        writeToFile(title, data, selectedFile);
    }

    /**
     * Write data to a chosen file.
     * @param title title of information alert
     * @param data data to be writen
     * @param selectedFile chosen file to write data to
     */
    public void writeToFile(String title, String data, File selectedFile) {
        // Initialize response alert.
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setHeaderText(null);

        PrintWriter outFile = null;

        try {
            outFile = new PrintWriter(selectedFile);
            outFile.println(data);
            outFile.close();

            // Alert the user about the success.
            alert.setContentText("Exported successfully " + title);
            alert.showAndWait();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

            // Alert the user about the failure.
            alert.setContentText("Error exporting " + title);
            alert.showAndWait();
        }
    }
}
