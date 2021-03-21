package nl.tudelft.oopp.demo.controllers.questions;

import java.text.SimpleDateFormat;
import java.util.Set;
import javafx.fxml.FXML;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextArea;

import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;




public class OwnQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;

    @FXML
    private MenuItem markAsAnsweredOption;
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

    /**
     * Takes the relevant information from the question,
     * user and app and creates a JavaFX object with all the relevant information.
     *
     * @param question the question asked by the user
     * @param user the user information in case of a need to ban
     * @param room the room information
     */
    public void loadData(Question question, User user, Room room) {
        if(question.getStatus().equals(Question.QuestionStatus.ANSWERED)){
            markAsAnsweredOption.setVisible(false);
        } else {
            markAsAnsweredOption.setVisible(true);
        }
        this.room = room;
        this.user = user;
        this.question = question;

        String simplifiedDate  = new SimpleDateFormat("HH:mm").format(question.getTimeCreated());
        date.setText(simplifiedDate);

        username.setText(" " + user.getUsername());
        questionText.setText(question.getText());
        upvoteNumber.setText(Integer.toString(question.getUpvotes()));

//        checkAlreadyUpvoted(user, question);
    }

    /**
     * Takes a question and increases/decreases the votes,
     * depending on the user interaction with the upvoting button.
     * Turns the button grey when upvoted and blue when decreased.
     */
    @FXML
    private void upvote() {
        if (upvoted) {
            QuestionViewCommunication.upvote(question.getId());

            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) - 1));
            upvoteButton.setStyle("-fx-text-fill: #00A6D6");
            upvoted = false;
        } else {
            QuestionViewCommunication.downvote(question.getId());

            upvoteNumber.setText(String.valueOf(Integer.parseInt(upvoteNumber.getText()) + 1));
            upvoteButton.setStyle("-fx-text-fill: #808080");
            upvoted = true;
        }
    }

    /**
     * Deletes the marked question.
     */
    public void deleteQuestion() {

    }

    /**
     * Marks the question as answered, hiding it from this.
     */
    public void questionAnswered() {
        QuestionViewCommunication.studentMarkAsAnswer(question.getId());

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

}

