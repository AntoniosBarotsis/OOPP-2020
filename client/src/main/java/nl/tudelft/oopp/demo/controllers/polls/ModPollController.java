package nl.tudelft.oopp.demo.controllers.polls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class ModPollController extends PollController {

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

        List<TextArea> anotherList = Arrays.asList(answerOneText, answerTwoText, answerThreeText, answerFourText,
                answerFiveText, answerSixText, answerSevenText, answerEightText, answerNineText, answerTenText);
        textList = new ArrayList<>();
        textList.addAll(anotherList);

        List<Button> anotherSelectorList = Arrays.asList(answerOneSelector, answerTwoSelector, answerThreeSelector,
                answerFourSelector, answerFiveSelector, answerSixSelector, answerSevenSelector, answerEightSelector,
                answerNineSelector, answerTenSelector);
        selectorList = new ArrayList<>();
        selectorList.addAll(anotherSelectorList);

        selected = new ArrayList<>();
        for (int i = 0; i < poll.getOptions().size(); i++){
            selected.add(false);
        }

        for (int i =0; i<poll.getOptions().size(); i++){
            if(!poll.getOptions().get(i).equals("")) {
                String currentOption = poll.getOptions().get(i);
                textList.get(i).setText(currentOption);
                if (poll.getCorrectAnswer().contains(currentOption)){
                    selected.set(i, true);
                    selectorList.get(i).setText("True");
                }
            }
        }


    }

    /**
     * Handles button "Options" clicks.
     */
    public void buttonOptionsClicked() {
        // TODO: button should launch a new window for Moderators
    }

    public void selectorClicked (int index) {
        selected.set(index, !selected.get(index));
        if(selected.get(index)){
            selectorList.get(index).setText("True");
        } else {
            selectorList.get(index).setText("False");
        }

    }

    public void selectorOne(ActionEvent actionEvent) {
        selectorClicked(0);
    }

    public void selectorTwo(ActionEvent actionEvent) {
        selectorClicked(1);
    }

    public void selectorThree(ActionEvent actionEvent) {
        selectorClicked(2);
    }

    public void selectorFour(ActionEvent actionEvent) {
        selectorClicked(3);
    }

    public void selectorFive(ActionEvent actionEvent) {
        selectorClicked(4);
    }

    public void selectorSix(ActionEvent actionEvent) {
        selectorClicked(5);
    }

    public void selectorSeven(ActionEvent actionEvent) {
        selectorClicked(6);
    }

    public void selectorEight(ActionEvent actionEvent) {
        selectorClicked(7);
    }

    public void selectorNine(ActionEvent actionEvent) {
        selectorClicked(8);
    }

    public void selectorTen(ActionEvent actionEvent) {
        selectorClicked(9);
    }

    public void submit(ActionEvent actionEvent) {
        List<String> options = new ArrayList<>();
        List<String> correctAnswers = new ArrayList<>();
        for (TextArea text: textList) {
            if (!text.equals("")) {
                options.add(text.getText());
            }
        }

        for (int i = 0; i < 10; i++) {
            if (selected.get(i) && !textList.get(i).getText().equals("")) {
                correctAnswers.add(textList.get(i).getText());
            }
        }
        poll.setCorrectAnswer(correctAnswers);
        poll.setOptions(options);
        poll.setText(questionText.getText());
        poll.setStatus(Poll.PollStatus.OPEN);

    }
}
