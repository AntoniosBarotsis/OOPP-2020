package nl.tudelft.oopp.demo.controllers.mainmenu;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.List;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.MenuButton;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;

import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import nl.tudelft.oopp.demo.communication.mainmenu.MainModCommunication;
import nl.tudelft.oopp.demo.controllers.polls.ModAskPollController;
import nl.tudelft.oopp.demo.controllers.polls.ModPollController;
import nl.tudelft.oopp.demo.controllers.questions.ModQuestionController;
import nl.tudelft.oopp.demo.controllers.questions.SimpleQuestionController;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class MainModController {

    private boolean filterPolls;
    private boolean filterAnswered;
    private boolean enableSimpleView;
    private List<Question> questionData;
    private List<Poll> pollData;
    private static List<FXMLLoader> loaderList;
    private Room room;
    private User user;
    private int refreshRate;
    private Timeline timelineRefresh;
    private boolean isSettingsOpen;

    @FXML
    private ListView<AnchorPane> questionList;
    @FXML
    private Text labelSlow;
    @FXML
    private Text labelFast;
    @FXML
    private Text labelNormal;
    @FXML
    private Button buttonAnswered;
    @FXML
    private Button buttonSimple;
    @FXML
    private Button buttonStartEnd;
    @FXML
    private Button buttonShowPolls;
    @FXML
    private Button buttonMakePolls;
    @FXML
    private Button buttonSettings;
    @FXML
    private MenuButton buttonExport;

    /**
     * Injects data from previous scene into current MainMenu.
     * @param room current room
     * @param user current user
     */
    public void loadData(Room room, User user) {
        // Initialize refresh rate of 0.
        refreshRate = 0;
        filterPolls = false;
        filterAnswered = false;
        enableSimpleView = false;
        isSettingsOpen = false;
        loaderList = new ArrayList<>();
        fetchData(room, user);

        // Check for new refresh rate every 5 seconds.
        Timeline timeline = new Timeline(
                new KeyFrame(Duration.seconds(5), e -> repeatFetch(this.room, this.user))
        );
        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();

        // Only lecturer should be able to start/end a lecture.
        if (!user.getUserType().equals(User.UserType.LECTURER)) {
            buttonStartEnd.setVisible(false);
        }
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

        // Check if data was successfully fetched.
        if (this.room == null || this.user == null
                || this.room.getId() == 0 || this.user.getId() == 0) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error connecting to room and fetching data. "
                    + "The application will close!");
            alert.show();
            alert.setOnCloseRequest(e -> {
                Platform.exit();
            });

            return;
        }

        // Changing "Start/End lecture" text is only required if user is lecturer.
        if (user.getUserType().equals(User.UserType.LECTURER)) {
            changeOngoingLecture();
        }

        // Set pace labels using the data fetched from server.
        labelSlow.setText(String.valueOf(this.room.getTooSlow()));
        labelFast.setText(String.valueOf(this.room.getTooFast()));
        labelNormal.setText(String.valueOf(this.room.getNormalSpeed()));

        // Fetch questions from database.
        this.questionData = MainModCommunication.getQuestions(this.room.getId());

        // Fetch polls from database.
        this.pollData = MainModCommunication.getPolls(this.room.getId());

        // Sort questions by score.
        questionData.sort(Comparator.comparing(Question::getScore).reversed());

        // Sort polls by status.
        pollData.sort(Comparator.comparing(Poll::getStatus));

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
        if (room.getSettings().getModRefreshRate() != refreshRate) {
            // Stop the previous timeline.
            if (timelineRefresh != null) {
                timelineRefresh.stop();
            }

            // Update the refresh rate.
            refreshRate = room.getSettings().getModRefreshRate();

            // Initialize a new timeline with new refresh rate.
            timelineRefresh = new Timeline(
                    new KeyFrame(Duration.seconds(refreshRate), e -> fetchData(room, user))
            );
            timelineRefresh.setCycleCount(Animation.INDEFINITE);
            timelineRefresh.play();
        }
    }

    /**
     * Populates ListView with data.
     */
    protected void populateListView() {
        if (filterPolls) {
            loadPolls();
        } else {
            loadQuestions();
        }
    }

    /**
     * Populates ListView with Question data.
     */
    protected void loadQuestions() {
        // Check if any of the questions are being modified.
        for (FXMLLoader loader : loaderList) {
            if (loader.getController() instanceof ModQuestionController) {
                if (((ModQuestionController) loader.getController()).getModified()) {
                    // Stop population of questions if some is being modified.
                    return;
                }
            }
        }
        loaderList = new ArrayList<>();

        questionList.getItems().clear();
        for (Question question : questionData) {
            boolean isAnswered = question.getStatus().equals(Question.QuestionStatus.ANSWERED);
            if (enableSimpleView) {
                if (!filterAnswered && !isAnswered) {
                    questionList.getItems().add(loadSimpleQuestionView(question));
                } else if (filterAnswered && isAnswered) {
                    questionList.getItems().add(loadSimpleQuestionView(question));
                }
            } else {
                if (!filterAnswered && !isAnswered) {
                    questionList.getItems().add(loadModQuestionView(question));
                } else if (filterAnswered && isAnswered) {
                    questionList.getItems().add(loadModQuestionView(question));
                }
            }
        }
    }

    /**
     * Populates ListView with Poll data.
     */
    protected void loadPolls() {
        questionList.getItems().clear();
        for (Poll poll : pollData) {
            questionList.getItems().add(loadPollView(poll));
        }
    }

    /**
     * Loads a ModQuestionView.
     * @param question question to be injected
     * @return anchorPane with injected question
     */
    protected AnchorPane loadModQuestionView(Question question) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/questionView/modQuestionView.fxml"));
        loaderList.add(loader);
        try {
            AnchorPane pane = loader.load();
            ModQuestionController controller = loader.getController();
            controller.loadData(question, user, room);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AnchorPane();
    }

    /**
     * Loads a SimpleQuestionView.
     * @param question question to be injected
     * @return anchorPane with injected question
     */
    protected AnchorPane loadSimpleQuestionView(Question question) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/questionView/simplisticView.fxml"));
        loaderList.add(loader);
        try {
            AnchorPane pane = loader.load();
            SimpleQuestionController controller = loader.getController();
            controller.loadData(question, user, room);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AnchorPane();
    }

    /**
     * Loads a PollView for moderators.
     * @param poll poll to be injected
     * @return anchorPane with injected poll
     */
    protected AnchorPane loadPollView(Poll poll) {
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/pollView/pollView.fxml"));

        try {
            ModPollController controller = new ModPollController();
            loader.setController(controller);

            AnchorPane pane = loader.load();
            controller.loadData(poll, user, room);
            return pane;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return new AnchorPane();
    }

    /**
     * Handles button "Settings" clicks.
     */
    @FXML
    public void buttonSettingsClicked() throws IOException {
        // Check if Settings is open.
        if (isSettingsOpen) {
            return;
        }

        // Set Settings window as open.
        isSettingsOpen = true;

        // Initialize a loader.
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/mainmenu/settings.fxml"));
        Parent root = loader.load();
        SettingsController controller = loader.getController();

        // Inject the data.
        controller.loadData(room, user);

        // Assign options to loader.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.setTitle("Room settings");
        stage.setOnCloseRequest(e -> {
            // Set Settings window as closed.
            isSettingsOpen = false;
        });
        stage.show();
    }

    /**
     * Handles button "Simple" clicks.
     */
    @FXML
    public void buttonSimpleClicked() {
        enableSimpleView = !enableSimpleView;
        populateListView();
        if (enableSimpleView) {
            buttonSimple.setText("Moderator view");
        } else {
            buttonSimple.setText("Simple view");
        }

        // Button should change visibility only for lecturers.
        if (user.getUserType().equals(User.UserType.LECTURER)) {
            buttonStartEnd.setVisible(!buttonStartEnd.isVisible());
        }

        buttonExport.setVisible(!buttonExport.isVisible());
        setButtonVisibility(List.of(buttonSettings,
                buttonMakePolls, buttonShowPolls));
    }

    /**
     * Inverts the visibility of the buttons in the list.
     * @param buttons list of buttons
     */
    private static void setButtonVisibility(List<Button> buttons) {
        for (Button button : buttons) {
            button.setVisible(!button.isVisible());
        }
    }

    /**
     * Handles button "Start lecture" clicks.
     */
    @FXML
    public void buttonStartEndClicked() {
        room.setOngoing(!room.isOngoing());
        MainModCommunication.setOngoingLecture(room.getId(), user.getId());
        changeOngoingLecture();
    }

    /**
     * Inverts "Start/End lecture" button text.
     */
    public void changeOngoingLecture() {
        if (room.isOngoing()) {
            buttonStartEnd.setText("End lecture");
        } else {
            buttonStartEnd.setText("Start lecture");
        }
    }

    /**
     * Handles button "As a multiple choice" clicks.
     */
    @FXML
    public void buttonMakePollsClicked() throws IOException {
        // Initialize a loader.
        FXMLLoader loader = new FXMLLoader(getClass()
                .getResource("/pollView/pollModAskView.fxml"));
        Parent root = loader.load();
        ModAskPollController controller = loader.getController();


        // Inject the data.
        Poll poll = new Poll(0L, "", new Date(),
                Arrays.asList("", "", "", "", "", "", "", "", "", ""),
                new ArrayList<>(), Poll.PollStatus.OPEN);
        controller.loadData(poll, user, room);

        // Assign options to loader.
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    /**
     * Handles button "Show polls" clicks.
     */
    @FXML
    public void buttonShowPollsClicked() {
        filterPolls = !filterPolls;
        populateListView();
        if (filterPolls) {
            buttonShowPolls.setText("Show questions");
            buttonAnswered.setDisable(true);
        } else {
            buttonShowPolls.setText("Show polls");
            buttonAnswered.setDisable(false);
        }
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
        String title = "room log";
        String data = MainModCommunication.getRoomLog(this.room.getId());
        directoryChooser(title, data);
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
