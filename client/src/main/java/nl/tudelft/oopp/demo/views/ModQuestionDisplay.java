package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URL;
import java.util.Date;
import java.util.HashSet;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.controllers.questions.ModQuestionController;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class ModQuestionDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
                FXMLLoader loader = new FXMLLoader();
                URL xmlUrl = getClass().getResource("/questionView/modQuestionView.fxml");
                loader.setLocation(xmlUrl);
                Parent root = loader.load();
                ModQuestionController controller = loader.getController();
                Question.QuestionStatus open = Question.QuestionStatus.OPEN;
                Date date = new Date();
                Room room = new Room(1, "room", new Date(), false, 0, 0);
                User user = new User(1, "DanielPuente", null, new HashSet<>(),
                    User.UserType.MODERATOR);
                long id = 1;
                Question question = new Question(1, "this is a question 1000",
                        id,  0, 0, date, open,"");

                controller.loadData(question, user, room);

                primaryStage.setScene(new Scene(root));
               primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
