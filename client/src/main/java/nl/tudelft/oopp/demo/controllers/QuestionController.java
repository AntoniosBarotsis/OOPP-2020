package nl.tudelft.oopp.demo.controllers;


import javafx.fxml.FXML;
import javafx.scene.control.MenuButton;
import javafx.scene.control.TextArea;
import javafx.scene.text.TextFlow;
import nl.tudelft.oopp.demo.data.Question;

import javax.swing.text.html.ImageView;

public class QuestionController {

    private Question question;

    @FXML
    private TextFlow questionText;

    @FXML
    private ImageView upvoteButton;

    @FXML
    private TextArea score;

    @FXML
    private MenuButton options;

    public void loadDate(Question question){
        this.question = question;
    }

    public void upvote() {
    }

    public void deleteQuestion() {
    }

    public void questionAnswered(){
        System.out.println("Hello World");
    }

    public void edit() {
    }

    public void banUser() {
    }

    public void answer() {
    }
}
