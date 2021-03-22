package nl.tudelft.oopp.demo.communication.startscreen;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Date;

import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.User;

public class StartCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Create a room.
     * @param roomName Name of the room
     * @return new Room
     */
    public static Room createRoom(String roomName) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v2/rooms/create?username=admin&ip=ip"
                        + "&title=" + roomName)).build();
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
     * Create a scheduled room.
     * @param roomName Name of the room
     * @param date the date for the scheduling
     * @return newly created scheduled room
     */
    public static Room createScheduledRoom(String roomName, Date date) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v2/rooms/schedule?username=admin&ip=ip"
                        + "&title=" + roomName
                        + "&date=" + date.getTime())).build();
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
     * Joins a room and creates a new user.
     * @param code code of the room
     * @param username name of the new user
     * @return
     */
    public static User joinRoom(String code, String username) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v2/rooms/join?password=" + code
                        + "&username=" + username + "&ip=ip")).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), User.class);
    }
}
