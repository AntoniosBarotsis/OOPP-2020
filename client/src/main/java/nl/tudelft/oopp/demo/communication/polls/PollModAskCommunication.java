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

    private static HttpClient client = HttpClient.newBuilder().build();
    private static Gson gson = new Gson();

    private static final String link = "http://localhost:8080/api/v1/";


    /**
     * Creates a new poll in the backend with pollHelper as its values.
     * It creates this poll in room room.
     *
     * @param pollHelper the poll helper used to create the new poll
     * @param room the room
     * @return String mapping of poll
     */
    public static String createPoll(PollHelper pollHelper, Room room) {
        String url = link + "polls/create?";
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
        String url = link + "polls/status?";

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
        String url = link + "polls/answerOccurences?";
        url = url + "pollId=" + pollId;
        url = url + "&answer=" + answer;

        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return Integer.parseInt(response.body());
    }

    /**
     * Gets the number of students answered the poll.
     *
     * @param pollId the poll id.
     * @return the number of students who answered the poll.
     */
    public static int getNumAnswers(long pollId) {
        String url = link + "polls/numAnswers?";
        url = url + "pollId=" + pollId;
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return 0;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return Integer.parseInt(response.body());
    }
}
