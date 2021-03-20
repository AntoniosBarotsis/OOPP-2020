package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.io.IOException;
import java.util.Comparator;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.util.Duration;

import nl.tudelft.oopp.demo.communication.mainmenu.MainStudentCommunication;
import nl.tudelft.oopp.demo.controllers.questions.OthersQuestionController;
import nl.tudelft.oopp.demo.controllers.questions.OwnQuestionController;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;

public class MainStudentController {

    private boolean filterAnswered;
    private List<Question> questionData;
    private Room room;
    private User user;

    @FXML
    private ListView<AnchorPane> questionList;
    @FXML
    private Button buttonSlow;
    @FXML
    private Button buttonFast;
    @FXML
    private Button buttonNormal;
    @FXML
    private Button buttonSend;
    @FXML
    private Button buttonAnswered;
    @FXML
    private Text labelPaceMin;
    @FXML
    private Text labelPaceSec;
    @FXML
    private Text labelQuesMin;
    @FXML
    private Text labelQuesSec;
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

        // Event listener to limit question length to 254 characters only.
        textQuestion.setOnKeyTyped(e -> {
            if (textQuestion.getText().length() > 254) {
                textQuestion.setText(textQuestion.getText().substring(0, 254));
            }
        });
    }

    /**
     * Fetches data from server and injects in FXML.
     * @param room current room
     * @param user current user
     */
    protected void fetchData(Room room, User user) {
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
    protected void populateListView() {
        questionList.getItems().clear();
        for (Question question : questionData) {

            boolean isAnswered = question.getStatus().equals(Question.QuestionStatus.ANSWERED);
            boolean isAuthor = user.getId() == question.getAuthor().getId();

            // Filter answered/unanswered and own question or normal question.
            if ((!filterAnswered && !isAuthor) || (filterAnswered && isAnswered)) {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/questionView/questionView.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    OthersQuestionController controller = loader.getController();
                    controller.loadData(question, user, room);
                    questionList.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            } else if (!filterAnswered && !isAnswered) {
                FXMLLoader loader = new FXMLLoader(getClass()
                        .getResource("/questionView/ownQuestionView.fxml"));
                try {
                    AnchorPane pane = loader.load();
                    OwnQuestionController controller = loader.getController();
                    controller.loadData(question, user, room);
                    questionList.getItems().add(pane);
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    /**
     * Initialises a countdown animation.
     * @param labelMin label used for minutes
     * @param labelSec label used for seconds
     */
    protected void setCountdown(Text labelMin, Text labelSec) {
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
        // Disable buttons.
        buttonFast.setDisable(true);
        buttonSlow.setDisable(true);
        buttonNormal.setDisable(true);

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec);

        // Increase the tooFast counter.
        MainStudentCommunication.increaseTooFast(room.getId());

        // Decrease the tooFast counter and enable buttons in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    MainStudentCommunication.decreaseTooFast(room.getId());
                    buttonFast.setDisable(false);
                    buttonSlow.setDisable(false);
                    buttonNormal.setDisable(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles button "Too slow" clicks.
     */
    @FXML
    public void buttonSlowClicked() {
        // Disable buttons.
        buttonFast.setDisable(true);
        buttonSlow.setDisable(true);
        buttonNormal.setDisable(true);

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec);

        // Increase the tooSlow counter.
        MainStudentCommunication.increaseTooSlow(room.getId());

        // Decrease the tooSlow counter and enable buttons in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    MainStudentCommunication.decreaseTooSlow(room.getId());
                    buttonFast.setDisable(false);
                    buttonSlow.setDisable(false);
                    buttonNormal.setDisable(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles button "Normal" clicks.
     */
    @FXML
    public void buttonNormalClicked() {
        // Disable buttons.
        buttonFast.setDisable(true);
        buttonSlow.setDisable(true);
        buttonNormal.setDisable(true);

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec);

        // Increase the tooSlow counter.
        MainStudentCommunication.increaseNormal(room.getId());

        // Decrease the tooSlow counter and enable buttons in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    MainStudentCommunication.decreaseNormal(room.getId());
                    buttonFast.setDisable(false);
                    buttonSlow.setDisable(false);
                    buttonNormal.setDisable(false);
                })
        );
        timeline.play();
    }

    /**
     * Handles button "Send" clicks.
     */
    @FXML
    public void buttonSendClicked() {
        // Disable buttons.
        buttonSend.setDisable(true);
        textQuestion.setDisable(true);

        // Initialise the countdown.
        setCountdown(labelQuesMin, labelQuesSec);

        // Enable textBox and button in 5min.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(299), e -> {
                    buttonSend.setDisable(false);
                    textQuestion.setDisable(false);
                })
        );
        timeline.play();

        // Fetch the ip.
        String ip = MainStudentCommunication.getIp();

        // Create a helper student object.
        StudentHelper studentHelper = new StudentHelper(this.user.getUsername(),
                ip);

        // Create a helper question object.
        textQuestion.setText(textQuestion.getText().replace("\n", " "));
        String text = textQuestion.getText();
        System.out.println(text);
        QuestionHelper question = new QuestionHelper(text, studentHelper);

        // Send the data to server.
        MainStudentCommunication.sendQuestion(room.getId(), user.getId(), question);

        // Clear textQuestion contents.
        textQuestion.setText("");
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
}
