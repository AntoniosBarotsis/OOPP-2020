package nl.tudelft.oopp.demo.controllers.questions;

import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.HashSet;
import java.util.Iterator;
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
    private boolean modified;
    private String answer = "";
    private String originalQuestion = "";


    @FXML
    private Label date;

    @FXML
    private Label username;

    @FXML
    private TextArea questionText;

    @FXML
    private Button upvoteButton;

    @FXML
    private Label upvoteNumber;

    @FXML
    private TextArea answerBox;

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
        this.answer = question.getAnswer();
        this.originalQuestion = question.getText();

        String simplifiedDate  = new SimpleDateFormat("HH:mm").format(question.getTimeCreated());
        date.setText(simplifiedDate);

        username.setText(" " + user.getUsername());
        questionText.setText(question.getText());
        upvoteNumber.setText(Integer.toString(question.getUpvotes()));
        modified = false;

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
        modified = true;
        questionText.setEditable(true);
        questionText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {

                    questionText.setText(questionText.getText().replaceFirst("\n", ""));
                    originalQuestion = questionText.getText().substring(0, questionText.getText().indexOf("\n"));
                    System.out.println(originalQuestion);

                    questionText.setText(originalQuestion + "\n\nAnswer: \n" + answer);
                    questionText.setEditable(false);
                    modified = false;

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

    /**
     * Getter for modified.
     *
     * @return modified
     */
    public boolean getModifyed() {
        return modified;
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

        answerBox.setEditable(true);

        answerBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {

                    System.out.println("Hey");
                    answer = answerBox.getText();
                    answerBox.setText("");
                    questionText.setText(question.getText());
                    answerBox.setEditable(false);

                    questionText.setText(originalQuestion + "\n\nAnswer: \n" + answer);

                    question.setAnswer(answer);

                    try {
                        QuestionViewCommunication.setAnswer(question.getId(), answer);
                    } catch (UnsupportedEncodingException e) {
                        e.printStackTrace();
                    }
                }
            }

        });
    }

    /**
     * Deletes the marked question.
     */
    public void deleteQuestion() {
    }
}
