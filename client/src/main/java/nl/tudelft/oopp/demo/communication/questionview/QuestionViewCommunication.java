package nl.tudelft.oopp.demo.communication.questionview;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.charset.StandardCharsets;

public class QuestionViewCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();

    /**
     * Increments the value of upvote by 1 in the backend.
     *
     * @param id the question id
     */

    // Not finished
    public static void upvote(long id) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/upvote/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the question with questionId id to be marked as spam unless too popular with score of 5.
     *
     * @param id the question id
     */
    public static void userMarkAsAnswer(long id) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/studentSetAnswered/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Sets the question with questionId id to be marked as answered.
     *
     * @param id the question id
     */
    public static void modMarkAsAnswer(long id) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/setAnswered/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Marks the question as spam, and adds the ip of user to bannedIps.
     * NOT FINISHED
     *
     * @param id the question id.
     */

    public static void banUser(long id) {
        //setSpam(id);
    }


    /**
     * Increments the value of upvote by 1 in the backend.
     * NOT FINISHED
     *
     * @param id the question id
     */
    public static void downvote(long id) {
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/downvote/" + id)).build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Encodes question text and sends it to the backend to be decoded,
     * then made the new question text.
     *
     * @param id the question id
     * @param questionText the edited question text
     * @throws UnsupportedEncodingException if StandardCharsets.UTF_8 is not supported
     */
    public static void editText(long id, String questionText) throws UnsupportedEncodingException {
        String questionTextUrl = URLEncoder
                .encode(questionText, StandardCharsets.UTF_8.toString());
        HttpRequest request =  HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/setText/" + id + "/" + questionTextUrl))
                .build();
        try {
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void setAnswer(long id, String answer) throws UnsupportedEncodingException {
        String encodedAnswer = URLEncoder
                .encode(answer, StandardCharsets.UTF_8.toString());

        HttpRequest request = HttpRequest.newBuilder().GET()
                .uri(URI.create("http://localhost:8080/api/v1/questions/setAnswer/" + id + "/" + encodedAnswer))
                .build();

        try{
            client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
