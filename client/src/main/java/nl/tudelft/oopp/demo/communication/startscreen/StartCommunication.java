package nl.tudelft.oopp.demo.communication.startscreen;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Date;
import java.util.HashSet;

import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.helper.RoomHelper;

public class StartCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    private static final String url = "http://localhost:8080/api/v2/";

    /**
     * Create a room.
     * @param roomHelper room object
     * @return new Room
     */
    public static Room createRoom(RoomHelper roomHelper) {
        String link = url + "rooms/create";
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(roomHelper)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            RoomConfig roomConfig = new RoomConfig(5, 5, 300, 300);
            return new Room(0, "Error loading room", new Date(),
                    false, -1, -1, -1, false, roomConfig);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            RoomConfig roomConfig = new RoomConfig(5, 5, 300, 300);
            return new Room(0, "Error loading room", new Date(),
                    false, -1, -1, -1, false, roomConfig);
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Joins a room and creates a new user.
     * @param code code of the room
     * @param username name of the new user
     * @return user object
     */
    public static User joinRoom(String code, String username) {
        String link = url + "rooms/join?password=" + code.trim()
                + "&username=" + username.trim();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(link))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new User(0, "error", new HashSet<>(), new HashSet<>(),
                    User.UserType.STUDENT);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            return new User(0, "error", new HashSet<>(), new HashSet<>(),
                    User.UserType.STUDENT);
        }
        return gson.fromJson(response.body(), User.class);
    }

    /**
     * Get a room based on the code given.
     * @param code password for the room
     * @return A room
     */
    public static Room getRoom(String code) {
        String link = url + "rooms/getFromPass?password=" + code.trim();
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create(link)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            RoomConfig roomConfig = new RoomConfig(5, 5, 300, 300);
            return new Room(0, "Error loading room", new Date(),
                    false, -1, -1, -1, false, roomConfig);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            RoomConfig roomConfig = new RoomConfig(5, 5, 300, 300);
            return new Room(0, "Error loading room", new Date(),
                    false, -1, -1, -1, false, roomConfig);
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Get the private code of a room using roomId.
     * @param roomId the roomId for the room
     * @return String containing the private code
     */
    public static String getPrivateCode(Long roomId) {
        String link = url + "rooms/private?roomId=" + roomId;
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create(link)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "ErrorFetchingCode";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), String.class);
    }
}
