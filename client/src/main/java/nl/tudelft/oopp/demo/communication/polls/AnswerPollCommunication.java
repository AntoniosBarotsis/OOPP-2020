package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.helper.AnswerHelper;
import nl.tudelft.oopp.demo.data.helper.PollHelper;

public class AnswerPollCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Creating of a answer for a user.
     * @param answerHelper The helper used for creating answers
     */
    public static void createAnswer(AnswerHelper answerHelper) {
        String url = "http://localhost:8080/api/v1/answers/create?";

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
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
        }
    }


    /**
     * Get the poll.
     *
     * @param pollId the poll id
     * @return the poll
     */
    public static Poll getPoll(long pollId) {
        String url = "http://localhost:8080/api/v1/polls/get?id=" + pollId;
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return gson.fromJson(response.body(), Poll.class);
    }
}

