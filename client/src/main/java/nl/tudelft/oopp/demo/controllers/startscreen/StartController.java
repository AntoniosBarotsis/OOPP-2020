package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.stage.Stage;

public class StartController {


    @FXML
    private Button buttonJoin;



    /**
     * Open the create window.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonCreate() throws Exception {
        Stage stage = (Stage) buttonJoin.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/createRoomScene.fxml"));
        Scene scene = new Scene(root, 960, 574);
        stage.setScene(scene);
        stage.show();
    }

    /**
     * Open the join window.
     * @throws Exception if FXML loader fails
     */
    @FXML
    public void buttonJoin() throws Exception {
        Stage stage = (Stage) buttonJoin.getScene().getWindow();
        Parent root = FXMLLoader.load(getClass().getResource("/startView/joinRoomScene.fxml"));
        Scene scene = new Scene(root, 960, 574);
        stage.setScene(scene);
        stage.show();
    }
}
