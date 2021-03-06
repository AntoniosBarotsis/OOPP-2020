package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Date;

import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.helper.AnswerHelper;
import nl.tudelft.oopp.demo.data.helper.PollHelper;

public class AnswerPollCommunication {

    private static final String url = "http://localhost:8080/api/v1/";

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Creating of a answer for a user.
     * @param answerHelper The helper used for creating answers
     */
    public static void createAnswer(AnswerHelper answerHelper) {
        String link = url + "answers/create?";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(link))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(answerHelper)))
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
     * Get the poll.
     *
     * @param pollId the poll id
     * @return the poll
     */
    public static Poll getPoll(long pollId) {
        String link = url + "polls/get?id=" + pollId;

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
            return new Poll("Error loading poll", Arrays.asList("Error"),
                    Arrays.asList("Error"), Poll.PollStatus.CLOSED);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return new Poll("Error loading poll", Arrays.asList("Error"),
                    Arrays.asList("Error"), Poll.PollStatus.CLOSED);
        }
        return gson.fromJson(response.body(), Poll.class);
    }

    /**
     * Gets the amount of answers of 1 answer.
     * @param pollId The poll the question was asked in
     * @param answer The answer
     * @return Amount of answers
     */
    public static int getAnswerAmount(long pollId, String answer) {
        String link = url + "polls/answerOccurrences?pollId=" + pollId
                + "&answer=" + URLEncoder.encode(answer, StandardCharsets.UTF_8);

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
            System.out.println();
            return -1;
        }
        return gson.fromJson(response.body(), int.class);
    }

    /**
     * Get the total amount of answers from a poll.
     * @param pollId The id of the poll
     * @return the total amount of answers
     */
    public static int getTotalAnswerAmount(long pollId) {
        String link = url + "polls/numAnswers?pollId=" + pollId;

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
        return gson.fromJson(response.body(), int.class);
    }

    /**
     * Check if the question has already been answered by a user.
     * @param pollId The poll
     * @param userId The user trying to answer
     * @return
     */
    public static boolean hasAnswered(long pollId, long userId) {
        String link = url + "answers/hasAnswered?pollId=" + pollId
                + "&userId=" + userId;

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
            return false;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
            return false;
        }
        return gson.fromJson(response.body(), boolean.class);
    }
}



