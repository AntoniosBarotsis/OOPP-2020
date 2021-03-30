package nl.tudelft.oopp.demo.controllers.polls;

import java.util.ArrayList;
import java.util.List;

import javafx.fxml.FXML;

import javafx.scene.Cursor;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;



public class AnswerPollController {

    @FXML
    private ListView<Button> listLeft;
    @FXML
    private ListView<Button> listRight;

    private String background = "-fx-background-color: LightBlue";
    private String highlightedBackground = "-fx-background-color: Green";
    private int correct;
    private List<Button> selected = new ArrayList<>();

    /**
     * Load data into the poll.
     * @param questions List containing all the questions
     */
    public void loadData(List<String> questions) {
        if (questions.size() <= 2) {
            fillLists(questions, 342);
        } else if (questions.size() <= 4) {
            fillLists(questions, 168);
        } else if (questions.size() <= 6) {
            fillLists(questions, 110);
        } else if (questions.size() <= 8) {
            fillLists(questions, 81);
        } else if (questions.size() <= 10) {
            fillLists(questions, 63);
        } else {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setHeaderText(null);
            alert.setContentText("Error inserting questions!");
            alert.showAndWait();
        }
    }

    /**
     * Fills the listviews with buttons.
     * @param questions List containing the questions
     * @param height The height that a button can be
     */
    public void fillLists(List<String> questions, int height) {
        for (int i = 0; i < questions.size(); i++) {
            if (i % 2 == 0) {
                listLeft.getItems().add(buttonCreator(questions.get(i), height));
            } else {
                listRight.getItems().add(buttonCreator(questions.get(i), height));
            }
        }
    }

    /**
     * Creates a button based on height.
     * @param value the questions that the button represents
     * @param height the height that the button can be
     * @return
     */
    public Button buttonCreator(String value, int height) {
        Button button = new Button(value);
        button.setPrefHeight(height);
        button.setPrefWidth(230);
        button.setStyle(background);
        button.setCursor(Cursor.HAND);
        button.setOnAction(e -> {
            Button button1 = (Button) e.getSource();
            if (selected.contains(button1)) {
                button1.setStyle(background);
                selected.remove(button1);
            } else {
                selected.add((button1));
                button1.setStyle(highlightedBackground);
            }
        });
        return button;
    }

}
