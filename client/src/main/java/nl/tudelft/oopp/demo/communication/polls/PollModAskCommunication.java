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

    /**
     * Checks whether the poll already exists in the backend.
     *
     * @param roomId the room id
     * @param pollId the poll id
     * @return A boolean of weather the poll already exists or not
     */
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

    /**
     * Updates the text, answers, and correct answers of the poll with id pollId.
     *
     * @param pollId the poll id
     * @param pollHelper the poll helper
     */
    public static void updatePoll(Long pollId, PollHelper pollHelper) {
        String url = "http://localhost:8080/api/v1/polls/update?";
        url = url + "pollId=" + pollId;

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
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }


    }

    /**
     * Gets the number of times students choose a specific answer in a question.
     * Add pollHelper
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
        String url = "http://localhost:8080/api/v1/polls/answerOccurences?";
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
        String url = "http://localhost:8080/api/v1/polls/getNumAnswers?";
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
