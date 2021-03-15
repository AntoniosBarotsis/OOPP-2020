package nl.tudelft.oopp.demo.controllers.questions;


import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;

import javafx.scene.image.ImageView;
import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.Question;


public class QuestionController {

    private Question question;

    @FXML
    private TextArea questionText;

    @FXML
    private ImageView upvoteButton;

    @FXML
    private TextArea score;

    @FXML
    private MenuButton options;

    public void loadData(Question question) {
        this.question = question;
        questionText.setText(question.getText());
        score.setText(Integer.toString(question.getScore()));
    }

    @FXML
    private void upvote() {

    }

    public void deleteQuestion() {
    }

    public void questionAnswered() {

    }

    public void edit() {
        questionText.setEditable(true);



    }

    public void banUser() {
        QuestionViewCommunication.banUser(question.getId());
    }

    public void answer() {
    }
}
