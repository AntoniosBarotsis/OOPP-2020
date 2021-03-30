package nl.tudelft.oopp.demo.communication.polls;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import nl.tudelft.oopp.demo.data.Poll;


public class PollModAskCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();
    private static Gson gson = new Gson();


    /**
     * Checks if a poll is already created, and if it isn't creates that poll.
     * Otherwise updates the information of the poll.
     *
     * @param poll the poll that is added in the backend.
     */
    public static void createPoll(Poll poll) {
        //Add checker for if poll already created, update information instead.
        String url = "http://localhost:8080/api/v1/polls/create?";
        url = url + "text=" + poll.getText();
        url = url + "&title=Title";
        url = url + "&options=" + poll.getOptions();
        url = url + "&answers=" + poll.getCorrectAnswer();

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
        }
    }
}
