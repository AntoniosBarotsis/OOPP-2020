package nl.tudelft.oopp.demo.controllers.questions;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;

import java.text.SimpleDateFormat;

public class ModQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean modified;
    private boolean hasPressedOption;
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
        //Shows the mark as answer option only if the question isn't already marked as answer
        if (question.getStatus().equals(Question.QuestionStatus.ANSWERED)) {
            markAsAnsweredOption.setVisible(false);
        } else {
            markAsAnsweredOption.setVisible(true);
        }
        this.room = room;
        this.user = user;
        this.question = question;
        this.answer = question.getAnswer();

        //Adds the answer to the answerBox only if question is answered
        if (!answer.equals("")) {
            answerBox.setText(answer);
            answerOption.setText("Edit Answer");
        } else {
            answerOption.setText("Answer");
            answerBox.setText("Write answer here: ");
        }

        //Check if another moderator is answering the question in order to avoid dual work
        if (question.getIsBeingAnswered()) {
            answerBox.setText("This question is already being answered");
            answerBox.setDisable(true);
            answerOption.setDisable(true);
        }
        if (!question.getIsBeingAnswered()) {
            if (!answer.equals("")) {
                answerBox.setText(answer);
                answerOption.setText("Edit Answer");
            } else {
                answerOption.setText("Answer");
                answerBox.setText("Write answer here: ");
            }
            answerBox.setDisable(false);
            answerOption.setDisable(false);
        }

        String simplifiedDate  = new SimpleDateFormat("HH:mm").format(question.getTimeCreated());
        date.setText(simplifiedDate);

        username.setText(" " + question.getAuthor().getUsername());
        questionText.setText(question.getText());
        upvoteNumber.setText(Integer.toString(question.getUpvotes()));
        modified = false;
        hasPressedOption = false;
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
        hasPressedOption = true;
        questionText.setEditable(true);
        questionText.requestFocus();
        questionText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    questionText.setText(questionText.getText().replaceAll("\n", " "));

                    hasPressedOption = false;
                    modified = false;
                    question.setText(questionText.getText().replaceAll("\n", " "));
                    StudentHelper studentHelper = new StudentHelper(user.getUsername(), "");

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
        QuestionViewCommunication.ban(room.getId(), user.getId(), question.getAuthor().getId());

    }

    /**
     * Allow answering of question.
     */
    public void answer() {

        if (QuestionViewCommunication.getBeingAnswered(question.getId())) {
            answerBox.setText("This question is already being answered");
            answerBox.setDisable(true);
            answerOption.setDisable(true);
        }
        question.setBeingAnswered(true);
        hasPressedOption = true;
        modified = true;

        QuestionViewCommunication.setBeingAnswered(question.getId(), true);

        answerBox.setEditable(true);
        answerBox.setText(answerBox.getText().replace("Write answer here: ", ""));
        answerBox.requestFocus();


        answerBox.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    answerBox.setEditable(false);
                    answerBox.setText(answerBox.getText().replaceAll("\n", " "));

                    hasPressedOption = false;
                    modified = false;
                    question.setBeingAnswered(false);

                    answer = answerBox.getText();

                    question.setAnswer(answer);

                    StudentHelper studentHelper = new StudentHelper(user.getUsername(), "");

                    QuestionHelper questionHelper = new QuestionHelper(
                            answerBox.getText(), studentHelper);

                    QuestionViewCommunication.setAnswer(question.getId(), questionHelper);
                    QuestionViewCommunication.setBeingAnswered(question.getId(), false);

                    answerOption.setText("Edit Answer");
                }
            }

        });
    }

    /**
     * Deletes the marked question.
     */
    public void deleteQuestion() {
        QuestionViewCommunication.delete(room.getId(), question.getId());
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
        if (!hasPressedOption) {
            modified = false;
        }
    }
}
