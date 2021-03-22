package nl.tudelft.oopp.demo.controllers.startscreen;

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
     * Open the create window.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonCreate() throws Exception {
        buttonJoin.setDisable(true);
        buttonCreate.setDisable(true);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/createRoomScene.fxml"));
        stage.setTitle("Create Screen");
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                buttonJoin.setDisable(false);
                buttonCreate.setDisable(false);
            }
        });
    }

    /**
     * Open the join window.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonJoin() throws Exception {
        buttonJoin.setDisable(true);
        buttonCreate.setDisable(true);
        Stage stage = new Stage();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/joinRoomScene.fxml"));
        stage.setTitle("Create Screen");
        stage.setScene(new Scene(root, 960, 540));
        stage.show();
        stage.setOnCloseRequest(new EventHandler<WindowEvent>() {
            public void handle(WindowEvent we) {
                buttonJoin.setDisable(false);
                buttonCreate.setDisable(false);
            }
        });
    }

}
