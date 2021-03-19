package nl.tudelft.oopp.demo.controllers.questions;

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

import java.text.SimpleDateFormat;


public class SimpleQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;

    @FXML
    private Label date;

    @FXML
    private Label username;

    @FXML
    private TextArea questionText;

    @FXML
    private Label upvotes;

    /**
     * Takes the relevant information from the question,
     * user and app and creates a JavaFX object with all the relevant information.
     *
     * @param question the question asked by the user
     * @param user the user information in case of a need to ban
     * @param room the room information
     */
    public void loadData(Question question, User user, Room room) {
        this.room = room;
        this.user = user;
        this.question = question;

        String simplifiedDate  = new SimpleDateFormat("HH:mm").format(question.getTimeCreated());
        date.setText(simplifiedDate);

        username.setText(" " + user.getUsername());
        questionText.setText(question.getText());
        upvotes.setText(Integer.toString(question.getUpvotes()));
    }


    /**
     * Marks the question as answered, hiding it from this.
     */
    public void questionAnswered() {
        QuestionViewCommunication.userMarkAsAnswer(question.getId());
    }

    /**
     * Takes an integer i and sets the upvotes label to that integer,
     * letting the lecturer know of the popularity of the question.
     * @param i the number of upvotes
     */
    public void setUpvotes(int i){
        upvotes.setText(Integer.toString(i));
    }
}

