package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;

public class StartController {

    @FXML
    private Button buttonJoin;
    @FXML
    private Button buttonCreate;

    /**
     * Action if the create button is clicked.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonCreate() throws Exception {
        buttonCreate.setDisable(true);
        createStage();
    }

    /**
     * Action if the join button is clicked.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonJoin() throws Exception {
        buttonJoin.setDisable(true);
        joinStage();
    }

    /**
     * Creates new create room window.
     * @throws Exception if fxml loader fails
     */
    public void createStage() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/createRoomScene.fxml"));
        stage.setTitle("Create Screen");
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                buttonCreate.setDisable(false);
            }
        });
    }

    /**
     * Creates new join room window.
     * @throws Exception if fxml loader fails
     */
    public void joinStage() throws Exception {
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/joinRoomScene.fxml"));
        stage.setTitle("Create Screen");
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                buttonJoin.setDisable(false);
            }
        });
    }

}
