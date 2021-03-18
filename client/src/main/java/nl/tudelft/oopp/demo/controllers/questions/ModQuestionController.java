package nl.tudelft.oopp.demo.controllers.questions;

import java.io.UnsupportedEncodingException;
import java.util.HashSet;
import java.util.Set;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class ModQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;
    private boolean modifyed;


    @FXML
    private Label date;

    @FXML
    private TextArea questionText;

    @FXML
    private Button upvoteButton;

    @FXML
    private Label upvoteNumber;

    /**
     * Loads the data of question user and room into controller as well as sets the date,
     * score and text into the fxml file.
     *
     * @param question the question entity
     * @param user the user entity
     * @param room the room entity
     */
    @FXML
    public void loadData(Question question, User user, Room room) {
        this.room = room;
        this.user = user;
        this.question = question;
        date.setText(question.getTimeCreated().toString());
        questionText.setText(question.getText());
        upvoteNumber.setText(Integer.toString(question.getUpvotes()));
        modifyed = false;

        checkAlreadyUpvoted(user, question);
    }

    /**
     * Takes a question and increases/decreases the votes,
     * depending on the user interaction with the upvoting button.
     * Turns the button grey when upvoted and blue when decreased.
     */
    @FXML
    private void upvote() {
        if (upvoted) {
            QuestionViewCommunication.downvote(question.getId());

            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) - 1));
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #00A6D6");

        } else {
            QuestionViewCommunication.upvote(question.getId());

            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) + 1));
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #808080");
        }
    }


    /**
     * Checks if the user already upvoted the question, and if the case the upvote
     * button turns grey and upvoted is true.
     *
     * @param user the User entity.
     * @param question the Question entity.
     */
    @FXML
    private void checkAlreadyUpvoted(User user, Question question) {
        //        Set<Question> upvotedQuestions = user.getQuestionsUpvoted();
        //        if (upvotedQuestions.contains(question)) {
        //            upvoted = true;
        //            upvoteButton.setStyle("-fx-text-fill: #808080");
        //        }
    }

    /**
     * Sets the question as spam in backend.
     */
    public void setAsSpam() {
        QuestionViewCommunication.setSpam(question.getId());
    }

    /**
     * Sets the question as answered in backend.
     */
    public void questionAnswered() {
        QuestionViewCommunication.modMarkAsAnswer(question.getId());
    }


    /**
     * Makes the questionText editable, then once enter is pressed,
     * it removed all new lines and updates the new question text both in the frontend and backend.
     * Lastly the questionText is made uneditable again.
     */
    public void edit() {
        modifyed = true;
        questionText.setEditable(true);
        questionText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    questionText.setText(questionText.getText().replaceAll("\n", ""));
                    questionText.setEditable(false);
                    modifyed = false;
                    try {
                        QuestionViewCommunication
                                .editText(question.getId(), questionText.getText());
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        });

    }

    public boolean getModifyed() {
        return modifyed;
    }

    /**
     * Bans the user in the backend.
     */
    public void banUser() {
    }

    /**
     * Allow answering of question.
     */
    public void answer() {
    }

    /**
     * Deletes the marked question.
     */
    public void deleteQuestion() {
    }
}
