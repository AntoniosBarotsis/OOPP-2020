package nl.tudelft.oopp.demo.controllers.questions;

import static com.sun.java.accessibility.util.AWTEventMonitor.addMouseListener;
import static com.sun.java.accessibility.util.AWTEventMonitor.removeMouseListener;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.text.SimpleDateFormat;
import java.util.Set;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.Button;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

import nl.tudelft.oopp.demo.communication.mainmenu.MainStudentCommunication;
import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;

public class ModQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;
    private boolean modified;
    private String answer;


    @FXML
    private MenuItem markAsAnsweredOption;

    @FXML
    private MenuItem answerOption;
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
        if(question.getStatus().equals(Question.QuestionStatus.ANSWERED)){
            markAsAnsweredOption.setVisible(false);
        } else {
            markAsAnsweredOption.setVisible(true);
        }
        this.room = room;
        this.user = user;
        this.question = question;
        this.answer = question.getAnswer();

        if (answer != "") {
            answerBox.setText(answer);
            answerOption.setText("Edit Answer");
        } else {
            answerOption.setText("Answer");
        }

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
            QuestionViewCommunication.removeQuestionUpvoted(question.getId(), user.getId());

            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) - 1));
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #00A6D6");

            user.removeQuestionUpvoted(question.getId());


        } else {
            QuestionViewCommunication.upvote(question.getId());
            QuestionViewCommunication.addQuestionUpvoted(question.getId(), user.getId());


            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) + 1));
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #808080");

            user.addQuestionUpvoted(question.getId());

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
        Set<Long> upvotedQuestions = user.getQuestionsUpvoted();

        if (upvotedQuestions != null && upvotedQuestions.contains(question.getId())) {
            upvoted = true;
            upvoteButton.setStyle("-fx-text-fill: #808080");
        }
    }

    /**
     * Sets the question as answered in backend.
     */
    public void questionAnswered() {
        QuestionViewCommunication.modMarkAsAnswer(question.getId());
        markAsAnsweredOption.setVisible(false);
    }


    /**
     * Makes the questionText editable, then once enter is pressed,
     * it removed all new lines and updates the new question text both in the frontend and backend.
     * Lastly the questionText is made uneditable again.
     */
    public void edit() {
        modified = true;
        questionText.setEditable(true);
        questionText.requestFocus();
        questionText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    questionText.setText(questionText.getText().replaceAll("\n", " "));

                    String ip = MainStudentCommunication.getIp();

                    StudentHelper studentHelper = new StudentHelper(user.getUsername(), ip);

                    QuestionHelper questionHelper = new QuestionHelper(
                            questionText.getText(), studentHelper);

                    questionText.setEditable(false);

                        QuestionViewCommunication
                                .setText(question.getId(), questionHelper);

                }
            }


        });


    }


    /**
     * Getter for modified.
     *
     * @return modified
     */
    public boolean getModified() {
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
        answerBox.requestFocus();

        answerBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    answerBox.setEditable(false);

                    answerBox.setText(answerBox.getText().replaceAll("\n", " "));


                    answer = answerBox.getText();


                    question.setAnswer(answer);

                    String ip = MainStudentCommunication.getIp();

                    StudentHelper studentHelper = new StudentHelper(user.getUsername(), ip);

                    QuestionHelper questionHelper = new QuestionHelper(
                            answerBox.getText(), studentHelper);

                    QuestionViewCommunication.setAnswer(question.getId(), questionHelper);

                }
            }

        });
    }

    /**
     * Deletes the marked question.
     */
    public void deleteQuestion() {
    }

    /**
     * Marks questionView as modified when options is clicked.
     */
    public void optionsClicked() {
        modified = true;
    }

    /**
     * Marks questionView as not modified when options hides.
     */
    public void optionsHidden() {
        modified = false;
    }
}
