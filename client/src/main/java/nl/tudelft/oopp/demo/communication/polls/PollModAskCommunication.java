package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.helper.PollHelper;


public class PollModAskCommunication {

    private static final String url = "http://localhost:8080/api/v1/";

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();


    /**
     * Creates a new poll in the backend with pollHelper as its values.
     * It creates this poll in room room.
     *
     * @param pollHelper the poll helper used to create the new poll
     * @param room the room
     * @return String mapping of poll
     */
    public static String createPoll(PollHelper pollHelper, Room room) {
        String link = url + "polls/create?"
                + "roomId=" + room.getId();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
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
        String link = url + "polls/status?"
                + "pollId=" + pollId
                + "&status=" + status.toString();

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
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
     * Gets the number of times students chose a specific answer to the poll
     * with id pollId.
     *
     * @param pollId the poll id.
     * @param answer the answer.
     * @return the number of people who selected that answer.
     */
    public static int getAnswerOccurences(long pollId, String answer) {
        try {
            answer = URLEncoder.encode(answer, StandardCharsets.UTF_8.toString());
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return -1;
        }
        String link = url + "polls/answerOccurrences?"
                + "pollId=" + pollId
                + "&answer=" + answer;

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
            return -1;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return -1;
        }
        return gson.fromJson(response.body(), Integer.class);
    }

    /**
     * Gets the number of students answered the poll.
     *
     * @param pollId the poll id.
     * @return the number of students who answered the poll.
     */
    public static int getNumAnswers(long pollId) {
        String link = url + "polls/numAnswers?"
                + "pollId=" + pollId;

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
            return -1;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return -1;
        }
        return gson.fromJson(response.body(), Integer.class);
    }
}
