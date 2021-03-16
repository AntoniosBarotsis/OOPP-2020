package nl.tudelft.oopp.demo.communication.mainmenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;

import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;

public abstract class MainMenuCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Request all questions in a room.
     * @param id id of a room
     * @return list of questions in a room
     */
    public static ArrayList<Question> getQuestions(long id) {
        String link = "http://localhost:8080/api/v1/rooms/questions?roomId=";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(link + id)).build();

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
        return gson.fromJson(response.body(), new TypeToken<ArrayList<Question>>(){}.getType());
    }

    /**
     * Request a room.
     * @param id id of a room
     * @return room information
     */
    public static Room getRoom(long id) {
        String link = "http://localhost:8080/api/v1/rooms/get?id=";
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(link + id)).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Room(0, "Error loading room", new Date(), false, -1, -1);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Request string data from server.
     * @param url endpoint of request
     * @return response body of request made
     */
    public static String requestStringData(String url) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed!";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Sends an empty PUT request to server.
     * @param url endpoint of request
     */
    public static void sendEmptyPutRequest(String url) {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
    }

    /**
     * Sends a POST request to server.
     * @param url endpoint of request
     * @param question question to be added.
     */
    public static void sendPostRequest(String url, QuestionHelper question) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(question)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
    }
}
