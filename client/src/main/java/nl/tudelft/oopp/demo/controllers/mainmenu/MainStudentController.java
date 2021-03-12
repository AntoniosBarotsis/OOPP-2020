package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.util.Comparator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.text.Text;
import javafx.util.Duration;

import nl.tudelft.oopp.demo.communication.mainmenu.MainStudentCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class MainStudentController {

    private List<Question> questionData;
    private Room room;
    private User user;

    @FXML
    private ListView<String> questionList;
    @FXML
    private Button buttonSlow;
    @FXML
    private Button buttonFast;
    @FXML
    private Text labelMin;
    @FXML
    private Text labelSec;
    @FXML
    private TextArea textQuestion;

    /**
     * Injects data from previous scene into current MainMenu.
     * @param room current room
     * @param user current user
     */
    public void loadData(Room room, User user) {
        fetchData(room, user);

        // Automatically fetch data every 5 seconds.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> fetchData(this.room, this.user))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Event listener to limit question length to 150 characters only.
        textQuestion.setOnKeyTyped(e -> {
            if (textQuestion.getText().length() > 150) {
                textQuestion.setText(textQuestion.getText().substring(0, 150));
            }
        });
    }

    /**
     * Fetches data from server and injects in FXML.
     * @param room current room
     * @param user current user
     */
    private void fetchData(Room room, User user) {
        // Fetch room data from server.
        this.room = MainStudentCommunication.getRoom(room.getId());
        this.user = user;

        // Fetch questions from database and load them into the ListView.
        this.questionData = MainStudentCommunication.getQuestions(this.room.getId());

        // Sort questions by score.
        questionData.sort(Comparator.comparing(Question::getScore).reversed());

        // Populate the ListView with the fetched data.
        populateListView();
    }

    /**
     * Populates ListView with Questions data.
     */
    private void populateListView() {
        questionList.getItems().clear();
        for (Question question : questionData) {
            /*
            TODO: questionList should be loaded with FMXL panels instead of string.
             */
            questionList.getItems().add(question.getText());
        }
    }

    /**
     * Initialises a countdown animation.
     */
    private void setCountdown() {
        // Disable buttons.
        buttonFast.setDisable(true);
        buttonSlow.setDisable(true);

        // Set starting timer.
        labelMin.setText(String.valueOf(4));
        labelSec.setText(String.valueOf(59));

        // Set minutes timer.
        Timeline timelineMinutes = new Timeline(
                new KeyFrame(Duration.seconds(60), e1 -> {
                    int current = Integer.parseInt(labelMin.getText());
                    labelMin.setText(String.valueOf(current - 1));
                })
        );
        timelineMinutes.setCycleCount(4);
        timelineMinutes.play();

        // Set seconds timer.
        Timeline timelineSeconds = new Timeline(
                new KeyFrame(Duration.seconds(1), e2 -> {
                    int current = Integer.parseInt(labelSec.getText());
                    if (current == 0) {
                        labelSec.setText(String.valueOf(59));
                    } else if (current < 11) {
                        labelSec.setText("0" + (current - 1));
                    } else {
                        labelSec.setText(String.valueOf(current - 1));
                    }
                })
        );
        timelineSeconds.setCycleCount(299);
        timelineSeconds.play();
    }

    /**
     * Handles button "Too fast" clicks.
     */
    @FXML
    public void buttonFastClicked() {
        // Initialise the countdown.
        setCountdown();

        // Increase the tooFast counter.
        MainStudentCommunication.increaseTooFast(room.getId());

        // Decrease the tooFast counter and enable buttons in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    MainStudentCommunication.decreaseTooFast(room.getId());
                    buttonFast.setDisable(false);
                    buttonSlow.setDisable(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles button "Too slow" clicks.
     */
    @FXML
    public void buttonSlowClicked() {
        // Initialise the countdown.
        setCountdown();

        // Increase the tooSlow counter.
        MainStudentCommunication.increaseTooSlow(room.getId());

        // Decrease the tooSlow counter and enable buttons in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    MainStudentCommunication.decreaseTooSlow(room.getId());
                    buttonFast.setDisable(false);
                    buttonSlow.setDisable(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles button "Send" clicks.
     */
    @FXML
    public void buttonSendClicked() {
        String question = textQuestion.getText();
        // TODO: implement message sending
    }
}
