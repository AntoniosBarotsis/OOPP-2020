package nl.tudelft.oopp.demo.communication.startscreen;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import java.util.Date;

import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.User;
import nl.tudelft.oopp.demo.data.deserializers.RoomInstanceCreator;

public class StartCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Create a room.
     * @param roomName Name of the room
     * @return new Room
     */
    public static Room createRoom(String roomName, String username) {
        String url = "http://localhost:8080/api/v2/rooms/create?username=" + username
                + "&title=" + roomName;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
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
            return null;
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Create a scheduled room.
     * @param roomName Name of the room
     * @param date the date for the scheduling
     * @return newly created scheduled room
     */
    public static Room createScheduledRoom(String roomName, String username, Date date) {
        String url = "http://localhost:8080/api/v2/rooms/schedule?username=" + username
                        + "&title=" + roomName
                        + "&date=" + date.getTime();
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
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
        String url = "http://localhost:8080/api/v2/rooms/join?password=" + code
                + "&username=" + username;
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create(url))
                .PUT(HttpRequest.BodyPublishers.ofString(""))
                .build();

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

    /**
     * Get a room based on the code given.
     * @param code password for the room
     * @return A room
     */
    public static Room getRoom(String code) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v2/rooms/getFromPass?password=" + code)).build();
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
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Get the private code of a room using roomId.
     * @param roomId the roomId for the room
     * @return String containing the private code
     */
    public static String getPrivateCode(Long roomId) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v2/rooms/private?roomId=" + roomId)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), String.class);
    }
}
