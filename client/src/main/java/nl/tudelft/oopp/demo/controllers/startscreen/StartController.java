package nl.tudelft.oopp.demo.controllers.startscreen;

import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class StartController {

    public void buttonCreate(ActionEvent event) throws Exception {
        createStage();
    }

    public void buttonJoin(ActionEvent event) throws Exception {
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
    }

}
