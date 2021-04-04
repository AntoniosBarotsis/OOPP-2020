package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.helper.PollHelper;


public class PollModAskCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();
    private static Gson gson = new Gson();



    /**
     * Checks if a poll is already created, and if it isn't creates that poll.
     * Otherwise updates the information of the poll.
     *
     * @param pollHelper the poll that is added in the backend.
     */
    public static String createPoll(PollHelper pollHelper, Room room) {
        //Add checker for if poll already created, update information instead.
        String url = "http://localhost:8080/api/v1/polls/create?";
        url = url + "roomId=" + room.getId();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(gson.toJson(pollHelper)))
                .build();



        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());


        } catch (Exception e) {
            e.printStackTrace();
            return "";
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }

    /**
     * Sets the status of the poll to the given one in the parameters.
     *
     * @param pollId the poll to change.
     * @param status The new status.
     */
    public static void setStatus(long pollId, Poll.PollStatus status) {
        String url = "http://localhost:8080/api/v1/polls/status?";

        url = url + "pollId=" + pollId;
        url = url + "&status=" + status.toString();

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
        }

    }

    public static Boolean doesExist(long roomId, long pollId) {
        String url = "http://localhost:8080/api/v1/polls/exists?";
        url = url + "roomId=" + roomId;
        url = url + "&pollId=" + pollId;

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return Boolean.parseBoolean(response.body());
    }
}
