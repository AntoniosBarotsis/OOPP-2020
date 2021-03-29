package nl.tudelft.oopp.demo.controllers.polls;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;

import java.util.List;

public class AnswerPollController {

    @FXML
    private ListView<Button> listLeft;
    @FXML
    private ListView<Button> listRight;

    private String background = "-fx-background-color: LightBlue";
    private String highlightedBackground = "-fx-background-color: Green";
    private int correct;
    private Button selected;

    public void loadData(List<String> questions, int index){
        correct = index;
        if (questions.size() <= 2) {
            fillLists(questions, 342);
        }
        else if (questions.size() <= 4) {
            fillLists(questions, 168);
        }
        else if (questions.size() <=6) {
            fillLists(questions, 110);
        }
        else if (questions.size() <= 8) {
            fillLists(questions, 81);
        }
        else if (questions.size() <= 10){
            fillLists(questions, 63);
        }
        else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error inserting questions!");
            alert.showAndWait();
        }

    }

    public void fillLists(List<String> questions, int height) {
        for (int i = 0; i < questions.size(); i++) {
            if (i % 2 == 0) {
                listLeft.getItems().add(buttonCreator(questions.get(i), height));
            }
            else {
                listRight.getItems().add(buttonCreator(questions.get(i), height));
            }
        }
    }

    public Button buttonCreator(String value, int height) {
        Button button = new Button(value);
        button.setPrefHeight(height);
        button.setPrefWidth(230);
        button.setStyle(background);
        button.setCursor(Cursor.HAND);
        button.setOnAction(e -> {
            if(selected != null){
                selected.setStyle(background);
            }
            selected = (Button) e.getSource();
            selected.setStyle(highlightedBackground);
        });
        return button;
    }

}
