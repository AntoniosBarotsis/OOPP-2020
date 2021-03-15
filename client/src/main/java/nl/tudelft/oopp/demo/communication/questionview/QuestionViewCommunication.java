package nl.tudelft.oopp.demo.communication.questionview;

import com.google.gson.Gson;
import javafx.scene.control.TextArea;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class QuestionViewCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    public static User getUser(long id){
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/getAuthor/" + id)).build();
        HttpResponse<String> response = null;

        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

        return gson.fromJson(response.body(), User.class);
    }

    public static void setSpam(long id){
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/setSpam/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void upvote(long id){
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/upvote/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void userMarkAsAnswer(long id){
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/user/setAnswered/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void modMarkAsAnswer(long id){
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/user/setAnswered/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    public static void banUser(long id){
        User user = getUser(id);
        setSpam(id);
    }

    public static void downvote(long id) {
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/downvote/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void editText(long id, String questionText) {
        HttpRequest request =  HttpRequest.newBuilder().GET().uri
                (URI.create("http://localhost:8080/api/v1/questions/setText/" + id + "/" + questionText)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
