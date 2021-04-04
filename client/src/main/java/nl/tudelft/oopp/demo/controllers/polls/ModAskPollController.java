package nl.tudelft.oopp.demo.controllers.polls;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.layout.AnchorPane;
import nl.tudelft.oopp.demo.communication.polls.PollModAskCommunication;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.PollHelper;


public class ModAskPollController {

    @FXML
    private AnchorPane scene;

    @FXML
    private TextArea questionText;

    @FXML
    private TextArea answerOneText;
    @FXML
    private TextArea answerTwoText;
    @FXML
    private TextArea answerThreeText;
    @FXML
    private TextArea answerFourText;
    @FXML
    private TextArea answerFiveText;
    @FXML
    private TextArea answerSixText;
    @FXML
    private TextArea answerSevenText;
    @FXML
    private TextArea answerEightText;
    @FXML
    private TextArea answerNineText;
    @FXML
    private TextArea answerTenText;

    @FXML
    private Button answerOneSelector;
    @FXML
    private Button answerTwoSelector;
    @FXML
    private Button answerThreeSelector;
    @FXML
    private Button answerFourSelector;
    @FXML
    private Button answerFiveSelector;
    @FXML
    private Button answerSixSelector;
    @FXML
    private Button answerSevenSelector;
    @FXML
    private Button answerEightSelector;
    @FXML
    private Button answerNineSelector;
    @FXML
    private Button answerTenSelector;

    @FXML
    private Button submitButton;
    @FXML
    private Button closePollButton;
    @FXML
    private Button showStatisticsButton;


    private Poll poll;
    private User user;
    private Room room;

    private ArrayList<TextArea> textList;
    private ArrayList<Button>  selectorList;
    private ArrayList<Boolean> selected;


    /**
     * Initialises a new Poll view for moderators.
     * @param poll current poll to display
     * @param user current user object
     * @param room current room object
     */
    public void loadData(Poll poll, User user, Room room) {
        this.poll = poll;
        this.user = user;
        this.room = room;

        List<TextArea> anotherList = Arrays.asList(answerOneText, answerTwoText, answerThreeText,
                answerFourText, answerFiveText, answerSixText, answerSevenText, answerEightText,
                answerNineText, answerTenText);
        textList = new ArrayList<>();
        textList.addAll(anotherList);

        List<Button> anotherSelectorList = Arrays.asList(answerOneSelector, answerTwoSelector,
                answerThreeSelector, answerFourSelector, answerFiveSelector, answerSixSelector,
                answerSevenSelector, answerEightSelector, answerNineSelector, answerTenSelector);
        selectorList = new ArrayList<>();
        selectorList.addAll(anotherSelectorList);

        selected = new ArrayList<>();
        for (int i = 0; i < poll.getOptions().size(); i++) {
            selected.add(false);
        }

        populateView(poll);
        poll = refreshPoll(poll);

        closePollButton.setDisable(true);
        showStatisticsButton.setDisable(true);
    }

    /**
     * Populates the view in a way where empty answers are ignored.
     *
     * @param poll the poll which the view will show.
     */
    private void populateView(Poll poll) {
        if (poll.getText().isEmpty()) {
            questionText.setText("");
        } else {
            questionText.setText(poll.getText());
        }

        int currentOption = 0;
        for (int i = 0; i < 10; i++) {
            if (poll.getOptions().size() > i) {
                String option = poll.getOptions().get(i);
                if (!option.isEmpty()) {
                    textList.get(currentOption).setText(option);

                    if (poll.getCorrectAnswer().contains(option)) {
                        setTrue(selectorList.get(currentOption));
                        selected.set(currentOption, true);
                    } else {
                        setFalse(selectorList.get(currentOption));
                        selected.set(currentOption, false);
                    }
                    currentOption++;
                }
            }
        }

        for (int i = currentOption; i < 10; i++) {
            textList.get(i).setText("");
            setFalse(selectorList.get(i));
        }
    }

    /**
     * Edits the poll to have the information from the view.
     *
     * @param poll the current poll
     * @return the poll after being refreshed to have the information from the view.
     */
    private Poll refreshPoll(Poll poll) {
        List<String> options = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        poll.setText(questionText.getText());

        for (int i = 0; i < 10; i++) {
            if (!textList.get(i).getText().isEmpty()) {
                options.add(textList.get(i).getText());
                if (selected.get(i)) {
                    correctAnswers.add(textList.get(i).getText());
                }
            }
        }
        poll.setOptions(options);
        poll.setCorrectAnswer(correctAnswers);
        return poll;
    }

    /**
     * Changes the text and colour to be true and green respectively.
     *
     * @param button the button that is changed.
     */
    private void setFalse(Button button) {
        button.setText("False");
        button.setStyle("-fx-background-color:#FFAAAA; -fx-background-width:1; "
                + "-fx-border-color:#AAAAAA");
    }

    /**
     * Changes the text and colour to be false and red respectively.
     *
     * @param button the button that is changed.
     */
    private void setTrue(Button button) {
        button.setText("True");
        button.setStyle("-fx-background-color: #AAFFAA; "
                + "-fx-background-width:1; -fx-border-color:#AAAAAA");

    }

    /**
     * Changes the selected to have the opposite boolean in index index.
     * Also changes the text of selectorist
     *
     * @param index the number of button clicked minus 1
     */
    public void selectorClicked(int index) {
        selected.set(index, !selected.get(index));
        if (selected.get(index)) {
            setTrue(selectorList.get(index));
        } else {
            setFalse(selectorList.get(index));
        }

    }


    /**
     * Calls selectorClicked with index 0.
     */
    public void selectorOne() {
        selectorClicked(0);
    }

    /**
     * Calls selectorClicked with index 1.
     */
    public void selectorTwo() {
        selectorClicked(1);
    }

    /**
     * Calls selectorClicked with index 2.
     */
    public void selectorThree() {
        selectorClicked(2);
    }

    /**
     * Calls selectorClicked with index 3.
     */
    public void selectorFour() {
        selectorClicked(3);
    }

    /**
     * Calls selectorClicked with index 4.
     */
    public void selectorFive() {
        selectorClicked(4);
    }

    /**
     * Calls selectorClicked with index 5.
     */
    public void selectorSix() {
        selectorClicked(5);
    }

    /**
     * Calls selectorClicked with index 6.
     */
    public void selectorSeven() {
        selectorClicked(6);
    }

    /**
     * Calls selectorClicked with index 7.
     */
    public void selectorEight() {
        selectorClicked(7);
    }

    /**
     * Calls selectorClicked with index 8.
     */
    public void selectorNine() {
        selectorClicked(8);
    }

    /**
     * Calls selectorClicked with index 9.
     */
    public void selectorTen() {
        selectorClicked(9);
    }

    /**
     * Updates poll with all the information found in pollModAskView.
     * Then creates a new poll with the info in the backend.
     */
    public void submit() {
        if (questionText.getText().isEmpty()) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("The poll must have a question text.");
            a.setHeaderText("No question text");
            a.show();
            return;
        }

        int trueCounter = 0;
        for (boolean a : selected) {
            if (a) {
                trueCounter++;
                break;
            }
        }
        if (trueCounter == 0) {
            Alert a = new Alert(Alert.AlertType.ERROR);
            a.setContentText("At least one question needs to be marked as true.");
            a.setHeaderText("Empty true error.");
            a.show();
            return;
        }

        questionText.setEditable(false);
        for (TextArea a: textList) {
            a.setEditable(false);
        }
        for (Button a: selectorList) {
            a.setDisable(true);
        }
        submitButton.setVisible(false);


        poll = refreshPoll(poll);
        populateView(poll);

        poll = refreshPoll(poll);
        poll.setStatus(Poll.PollStatus.OPEN);

        String text = questionText.getText();

        PollHelper pollHelper = new PollHelper(text, poll.getOptions(), poll.getCorrectAnswer());

        // Remember to check if the poll already exists :)
        Boolean exists = PollModAskCommunication.doesExist(room.getId(), poll.getId());
        if (exists) {
        //todo
        } else {
            String pollMap = PollModAskCommunication.createPoll(pollHelper, room);

            String id = pollMap.substring(pollMap.indexOf(":") + 1, pollMap.indexOf(","));
            poll.setId(Long.parseLong(id));
        }
        closePollButton.setDisable(false);
        showStatisticsButton.setDisable(false);
    }

    /**
     * Sets the status of the poll to closed.
     */
    public void closePoll() {
        submitButton.setVisible(true);

        questionText.setEditable(true);
        for (TextArea a: textList) {
            a.setEditable(true);
        }
        for (Button a: selectorList) {
            a.setDisable(false);
        }

        closePollButton.setDisable(true);
        PollModAskCommunication.setStatus(poll.getId(), Poll.PollStatus.CLOSED);
    }
}
