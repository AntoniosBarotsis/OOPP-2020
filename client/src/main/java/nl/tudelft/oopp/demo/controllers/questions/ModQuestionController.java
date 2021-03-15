package nl.tudelft.oopp.demo.controllers.questions;


import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import nl.tudelft.oopp.demo.communication.ServerCommunication;
import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;


public class ModQuestionController {

    private Question question;
    private User user;
    private Room room;
    private boolean upvoted = false;


    @FXML
    private Label date;

    @FXML
    private TextArea questionText;

    @FXML
    private Button upvoteButton;

    @FXML
    private TextArea score;

    @FXML
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
        if(upvoted) {
            score.setText(String.valueOf(Integer.parseInt(score.getText()) - 1));
            QuestionViewCommunication.downvote(question.getId());
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #00A6D6");

        } else {
            score.setText(String.valueOf(Integer.parseInt(score.getText()) + 1));
            QuestionViewCommunication.upvote(question.getId());
            upvoted = !upvoted;
            upvoteButton.setStyle("-fx-text-fill: #808080");
        }
    }

    public void setAsSpam() {
        QuestionViewCommunication.setSpam(question.getId());
    }

    public void questionAnswered() {
        QuestionViewCommunication.userMarkAsAnswer(question.getId());
    }


    public void edit() {
        questionText.setEditable(true);
        questionText.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent ke) {
                if (ke.getCode().equals(KeyCode.ENTER)) {
                    questionText.setText(questionText.getText().replaceAll("\n", ""));
                    questionText.setEditable(false);
                    QuestionViewCommunication.editText(question.getId(), questionText.getText());
                }
            }

        });

    }


    public void banUser() {
        User user = QuestionViewCommunication.getUser(question.getId());
        questionText.setText(user.getUsername());
//        QuestionViewCommunication.banUser(question.getId());
    }

    public void answer() {
    }
}
