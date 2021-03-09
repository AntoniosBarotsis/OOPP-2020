package nl.tudelft.oopp.demo.communication.mainmenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;

import nl.tudelft.oopp.demo.data.Question;

public class MainModCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Request student code for a room.
     * @param id id of a room
     * @return student code for a chosen room
     */
    public static String getStudentPassword(long id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api/v1/rooms/public/" + id)).build();
        return  requestStringData(request);
    }

    /**
     * Request moderator code for a room.
     * @param id id of a room
     * @return moderator code for a chosen room
     */
    public static String getAdminPassword(long id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api/v1/rooms/private/" + id)).build();
        return  requestStringData(request);
    }

    /**
     * Request all questions in a room.
     * @param id id of a room
     * @return list of questions in a room
     */
    public static ArrayList<Question> getQuestions(long id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api/v1/rooms/questions/" + id)).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<Question>();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), new TypeToken<ArrayList<Question>>(){}.getType());
    }

    /**
     * Request string data from server.
     * @param request httprequest to be made
     * @return response body of request made
     */
    public static String requestStringData(HttpRequest request) {
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }
}
