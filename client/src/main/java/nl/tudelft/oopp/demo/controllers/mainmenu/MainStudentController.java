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
    private int refreshRate;
    private Timeline timelineRefresh;

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
        // Initialize refresh rate of 0.
        refreshRate = 0;
        fetchData(room, user);

        // Check for new refresh rate every 5 seconds.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> repeatFetch(this.room, this.user))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Event listener to limit question length to 500 characters only.
        textQuestion.setOnKeyTyped(e -> {
            if (textQuestion.getText().length() > 500) {
                textQuestion.setText(textQuestion.getText().substring(0, 500));
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
     * Initializes data to be fetched with a certain delay.
     * @param room current room
     * @param user current user
     */
    protected void repeatFetch(Room room, User user) {
        // Check if the refresh rate for questions has changed.
        if (room.getSettings().getStudentRefreshRate() != refreshRate) {
            // Stop the previous timeline.
            if (timelineRefresh != null) {
                timelineRefresh.stop();
            }

            // Update the refresh rate.
            refreshRate = room.getSettings().getStudentRefreshRate();

            // Initialize a new timeline with new refresh rate.
            timelineRefresh = new Timeline(
                    new KeyFrame(Duration.seconds(refreshRate), e -> fetchData(room, user))
            );
            timelineRefresh.setCycleCount(Animation.INDEFINITE);
            timelineRefresh.play();
        }
    }

    /**
     * Populates ListView with Questions data.
     */
    protected void populateListView() {
        questionList.getItems().clear();
        for (Question question : questionData) {

            boolean isAnswered = question.getStatus().equals(Question.QuestionStatus.ANSWERED);
            boolean isAuthor = user.getId() == question.getAuthor().getId();

            // Filter answered/unanswered.
            if (!filterAnswered && !isAnswered) {
                // Filter own and other question.
                if (!isAuthor) {
                    questionList.getItems().add(loadOthersQuestionView(question));
                } else {
                    questionList.getItems().add(loadOwnQuestionView(question));
                }
            } else if (filterAnswered && isAnswered) {
                questionList.getItems().add(loadOthersQuestionView(question));
            }
        }
    }

    /**
     * Initialises a countdown animation.
     * @param labelMin label used for minutes
     * @param labelSec label used for seconds
     * @param duration duration of countdown
     */
    protected void setCountdown(Text labelMin, Text labelSec, int duration) {
        // Set starting minutes timer.
        int minuteDuration = (duration-1) / 60;
        labelMin.setText(String.valueOf(minuteDuration));

        // Set starting seconds timer.
        int secondDuration = (duration-1) % 60;
        if (secondDuration < 10) {
            labelSec.setText("0" + secondDuration);
        } else {
            labelSec.setText(String.valueOf(secondDuration));
        }

        // Set minutes timer.
        Timeline timelineMinutes = new Timeline(
                new KeyFrame(Duration.seconds(duration - minuteDuration * 60), e1 -> {
                    int current = Integer.parseInt(labelMin.getText()) - 1;
                    if (current < 0) {
                        current = 0;
                    }
                    labelMin.setText(String.valueOf(current));

                    Timeline internal = new Timeline(
                            new KeyFrame(Duration.seconds(60), e2 -> {
                                int currentInternal = Integer.parseInt(labelMin.getText()) - 1;
                                if (currentInternal < 0) {
                                    currentInternal = 0;
                                }
                                labelMin.setText(String.valueOf(currentInternal));
                            })
                    );
                    internal.setCycleCount(minuteDuration - 1);
                    internal.play();
                })
        );
        timelineMinutes.setCycleCount(1);
        timelineMinutes.play();

        // Set seconds timer.
        Timeline timelineSeconds = new Timeline(
                new KeyFrame(Duration.seconds(1), e2 -> {
                    int current = Integer.parseInt(labelSec.getText());
                    if (current <= 0) {
                        labelSec.setText(String.valueOf(59));
                    } else if (current < 11) {
                        labelSec.setText("0" + (current - 1));
                    } else {
                        labelSec.setText(String.valueOf(current - 1));
                    }
                })
        );
        timelineSeconds.setCycleCount(duration - 1);
        timelineSeconds.play();
    }

    /**
     * Handles button "Too fast" clicks.
     */
    @FXML
    public void buttonFastClicked() {
        // Disable buttons.
        setButtonDisabled();

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec, room.getSettings().getPaceCooldown());

        // Increase the tooFast counter.
        MainStudentCommunication.increaseTooFast(room.getId());

        // Decrease the tooFast counter and enable buttons.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(room.getSettings().getPaceCooldown() - 1), e -> {
                    MainStudentCommunication.decreaseTooFast(room.getId());
                    setButtonDisabled();
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
        setButtonDisabled();

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec, room.getSettings().getPaceCooldown());

        // Increase the tooSlow counter.
        MainStudentCommunication.increaseTooSlow(room.getId());

        // Decrease the tooSlow counter and enable buttons.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(room.getSettings().getPaceCooldown() - 1), e -> {
                    MainStudentCommunication.decreaseTooSlow(room.getId());
                    setButtonDisabled();
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
        setButtonDisabled();

        // Initialise the countdown.
        setCountdown(labelPaceMin, labelPaceSec, room.getSettings().getPaceCooldown());

        // Increase the tooSlow counter.
        MainStudentCommunication.increaseNormal(room.getId());

        // Decrease the tooSlow counter and enable buttons.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(room.getSettings().getPaceCooldown() - 1), e -> {
                    MainStudentCommunication.decreaseNormal(room.getId());
                    setButtonDisabled();
                })
        );
        timeline.play();
    }

    /**
     * Inverts button enabled/disabled.
     */
    protected void setButtonDisabled() {
        buttonFast.setDisable(!buttonFast.isDisabled());
        buttonSlow.setDisable(!buttonSlow.isDisabled());
        buttonNormal.setDisable(!buttonNormal.isDisabled());
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
        setCountdown(labelQuesMin, labelQuesSec, room.getSettings().getQuestionCooldown());

        // Enable textBox and button.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(room.getSettings().getQuestionCooldown() - 1), e -> {
                    buttonSend.setDisable(false);
                    textQuestion.setDisable(false);
                })
        );
        timeline.play();

        // Replace newlines from question with " ".
        textQuestion.setText(textQuestion.getText().replace("\n", " "));
        String text = textQuestion.getText();

        // Create a helper student object.
        StudentHelper studentHelper = new StudentHelper(this.user.getUsername(), "");

        // Create a helper question object.
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

    /**
     * Loads an OwnQuestionView.
     * @param question question to be injected
     * @return anchorPane with injected question
     */
    protected AnchorPane loadOwnQuestionView(Question question) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/questionView/ownQuestionView.fxml"));
        try {
            AnchorPane pane = loader.load();
            OwnQuestionController controller = loader.getController();
            controller.loadData(question, user, room);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AnchorPane();
    }

    /**
     * Loads an OthersQuestionView.
     * @param question question to be injected
     * @return anchorPane with injected question
     */
    protected AnchorPane loadOthersQuestionView(Question question) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/questionView/questionView.fxml"));
        try {
            AnchorPane pane = loader.load();
            OthersQuestionController controller = loader.getController();
            controller.loadData(question, user, room);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AnchorPane();
    }
}
