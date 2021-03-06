package nl.tudelft.oopp.demo.controllers.questions;

import java.text.SimpleDateFormat;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;



public class SimpleQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;

    @FXML
    private Button markAsAnsweredOption;

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

        username.setText(" " + question.getAuthor().getUsername());
        questionText.setText(question.getText());
        upvotes.setText(Integer.toString(question.getUpvotes()));

        //Shows the mark as answer option only if the question isn't already marked as answer
        if (question.getStatus().equals(Question.QuestionStatus.ANSWERED)) {
            markAsAnsweredOption.setVisible(false);
        } else {
            markAsAnsweredOption.setVisible(true);
        }

        //Adds the answer to the answerBox only if question is answered
        if (!question.getAnswer().equals("")) {
            questionText.setText(questionText.getText() + "\n\nAnswer:\n"
                    + question.getAnswer());
        }

    }


    /**
     * Marks the question as answered, hiding it from this.
     */
    public void questionAnswered() {
        QuestionViewCommunication.modMarkAsAnswer(question.getId());
        markAsAnsweredOption.setVisible(false);
    }


}

