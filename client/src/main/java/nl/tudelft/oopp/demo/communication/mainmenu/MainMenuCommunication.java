package nl.tudelft.oopp.demo.communication.mainmenu;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.ArrayList;
import java.util.Date;

import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;

public abstract class MainMenuCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    private static final String url = "http://localhost:8080/api/v2/";

    /**
     * Request all questions in a room.
     * @param id id of a room
     * @return list of questions in a room
     */
    public static ArrayList<Question> getQuestions(long id) {
        String link = url + "rooms/questions?roomId=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return new ArrayList<>();
        }
        return gson.fromJson(response.body(), new TypeToken<ArrayList<Question>>(){}.getType());
    }

    /**
     * Request all polls in a room.
     * @param id id of a room
     * @return list of polls in a room
     */
    public static ArrayList<Poll> getPolls(long id) {
        String link = url + "rooms/polls?roomId=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return gson.fromJson(response.body(), new TypeToken<ArrayList<Poll>>(){}.getType());
    }

    /**
     * Request a room.
     * @param id id of a room
     * @return room information
     */
    public static Room getRoom(long id) {
        String link = url + "rooms/get?id=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            RoomConfig settings = new RoomConfig(1000, 1000, 600, 600);
            return new Room(0, "Error loading room", new Date(), false, -1,
                    -1, -1, false, settings);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            RoomConfig roomConfig = new RoomConfig(5, 5, 300, 300);
            return new Room(0, "Error loading room", new Date(),
                    false, -1, -1, -1, false, roomConfig);
        }
        return gson.fromJson(response.body(), Room.class);
    }

    /**
     * Request string data from server.
     * @param url endpoint of request
     * @return response body of request made
     */
    public static String requestStringData(String url) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .GET()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return "Communication with server failed!";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return response.body();
    }

    /**
     * Sends an empty PUT request to server.
     * @param url endpoint of request
     */
    public static void sendEmptyPutRequest(String url) {
        HttpRequest request = HttpRequest
                .newBuilder()
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
            System.out.println(response.body());
        }
    }

    /**
     * Sends a POST request to server.
     * @param url endpoint of request
     * @param question question to be added.
     */
    public static String sendPostRequest(String url, QuestionHelper question) {
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
            return "error";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return "error";
        }

        return "success";
    }

    /**
     * Sends a PUT request to server.
     * @param url endpoint of request
     * @param roomConfig settings of room
     * @return status code or exception message
     */
    public static String sendPutRequestRoomConfig(String url, RoomConfig roomConfig) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(roomConfig)))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }

        return String.valueOf(response.statusCode());
    }

    /**
     * Sends a DELETE request to server.
     * @param url endpoint of request
     * @return status code or exception message
     */
    public static String sendDeleteRequest(String url) {
        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .DELETE()
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return e.getMessage();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }

        return String.valueOf(response.statusCode());
    }
}
