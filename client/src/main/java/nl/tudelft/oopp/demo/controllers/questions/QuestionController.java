package nl.tudelft.oopp.demo.controllers.questions;


import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class QuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;

    @FXML
    private Label date;

    @FXML
    private TextArea questionText;

    @FXML
    private ImageView upvoteButton;

    @FXML
    private TextArea score;

    public void loadData(Question question, User user, Room room) {
        this.room = room;
        this.user = user;
        this.question = question;
        date.setText(question.getTimeCreated().toString());
        questionText.setText(question.getText());
        score.setText(Integer.toString(question.getScore()));
    }


    @FXML
    private void upvote() {

        if(upvoted){
            //QuestionViewCommunication.upvote(question.getId());
            score.setText(String.valueOf(Integer.parseInt(score.getText()) - 1));
            upvoteButton.setStyle("-fx-text-fill: #00A6D6");
            upvoted = false;
        }
        else{
            //QuestionViewCommunication.downvote(question.getId());
            score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
            upvoteButton.setStyle("-fx-text-fill: #808080");
            upvoted = true;
        }
    }
}
