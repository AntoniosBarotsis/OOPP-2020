package nl.tudelft.oopp.demo.views;

import java.io.IOException;
import java.net.URI;
import java.net.URL;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import nl.tudelft.oopp.demo.communication.mainmenu.MainMenuCommunication;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainModController;
import nl.tudelft.oopp.demo.controllers.mainmenu.MainStudentController;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class QuoteDisplay extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        FXMLLoader loader = new FXMLLoader();
        URL xmlUrl = getClass().getResource("/mainmenu/mainModScene.fxml");
//        URL xmlUrl = getClass().getResource("/mainmenu/mainStudentScene.fxml");
        loader.setLocation(xmlUrl);

        Parent root = loader.load();

        MainModController controller = loader.getController();
//        MainStudentController controller = loader.getController();

        // Test if fetching users is working
        for(User user: getUsers()){
            System.out.println(user.getId() + " " + user.getUsername() + " " + user.getUserType());
            for(long id: user.getQuestionsAsked()){
                System.out.print(id);
            }
            System.out.println();
            for(long id: user.getQuestionsUpvoted()){
                System.out.print(id);
            }
        }

        // Load the menus with data
        Room room = new Room(1, "room", new Date(), false, 0, 0);
        User user = new User(3, "Student",  new HashSet<>(), new HashSet<>(), User.UserType.STUDENT);
        controller.loadData(room, user);


        primaryStage.setScene(new Scene(root));
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }

    // Fetch users
    private static HttpClient client = HttpClient.newBuilder().build();
    private static Gson gson = new Gson();
    public static ArrayList<User> getUsers() {
        String link = "http://localhost:8080/api/v1/users/";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(link)).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), new TypeToken<ArrayList<User>>(){}.getType());
    }
}