package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.demo.data.Poll;

public class AnswerPollCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Creating of a answer for a user.
     * @param answers Correct format list (*String1*,*String2*, etc.)
     * @param pollId the id of the poll
     * @param userId the id of the user
     */
    public static void createAnswer(String answers, long pollId, long userId) {
        String url = "http://localhost:8080/api/v1/answers/create?answers=" + answers.trim()
                + "&pollId=" + pollId
                + "&userId=" + userId;
        HttpRequest request = HttpRequest.newBuilder()
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
            return;
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

