package nl.tudelft.oopp.demo.communication.questionview;

import com.google.gson.Gson;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

import nl.tudelft.oopp.demo.communication.mainmenu.MainModCommunication;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;



public class QuestionViewCommunication {

    private static HttpClient client = HttpClient.newBuilder().build();
    private static Gson gson = new Gson();


    /**
     * Sends an empty PUT request to server.
     * @param url endpoint of request
     */
    public static void sendEmptyPutRequest(String url) {
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


    /**
     * Increments the value of upvote by 1 in the backend.
     *
     * @param id the question id
     */

    // Not finished
    public static void upvote(long id) {
        String url = "http://localhost:8080/api/v2/questions/upvote?";
        url = url + "questionId=" + id;
        sendEmptyPutRequest(url);
    }

    /**
     * Sets the question with questionId id to be marked as answered
     * unless too popular with score of 5.
     *
     * @param id the question id
     */
    public static void studentMarkAsAnswer(long id) {
        String url = "http://localhost:8080/api/v2/questions/studentSetAsAnswered?";
        url = url + "questionId=" + id;
        sendEmptyPutRequest(url);

    }

    /**
     * Sets the question with questionId id to be marked as answered.
     *
     * @param id the question id
     */
    public static void modMarkAsAnswer(long id) {
        String url = "http://localhost:8080/api/v2/questions/setAnswered?";
        url = url + "questionId=" + id;
        sendEmptyPutRequest(url);

    }

    /**
     * Adds the ip of user to bannedIps.
     * NOT FINISHED
     *
     * @param id the question id.
     */

    public static void banUser(long id) {
    }


    /**
     * Increments the value of upvote by 1 in the backend.
     * NOT FINISHED
     *
     * @param id the question id
     */
    public static void downvote(long id) {
        String url = "http://localhost:8080/api/v2/questions/downvote?";
        url = url + "questionId=" + id;
        sendEmptyPutRequest(url);

    }

    /**
     * Sets the text of question with id as the text in questionHelper.
     *
     * @param id the question id
     * @param questionHelper questionHelper with the edited question text
     */
    public static void setText(long id, QuestionHelper questionHelper)  {
        String url = "http://localhost:8080/api/v2/questions/setText?";
        url = url + "questionId=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(questionHelper)))
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
     * Sets the text of question with id as the text in questionHelper.
     *
     * @param id the question id
     * @param questionHelper questionHelper with the answer text
     */
    public static void setAnswer(long id, QuestionHelper questionHelper)  {
        String url = "http://localhost:8080/api/v2/questions/setAnswer?";
        url = url + "questionId=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(questionHelper)))
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
     * Adds the questionId to the upvotedQuestions of user with userId.
     *
     * @param questionId the question id
     * @param userId the user id
     */
    public static void addQuestionUpvoted(long questionId, long userId) {

        String url = "http://localhost:8080/api/v1/users/addUpvotedQuestion?";
        url = url + "userId=" + userId;
        url = url + "&questionId=" + questionId;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(""))
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
     * Removes the questionId to the upvotedQuestions of user with userId.
     *
     * @param questionId the question id.
     * @param userId the user id.
     */
    public static void removeQuestionUpvoted(long questionId, long userId) {

        String url = "http://localhost:8080/api/v1/users/removeUpvotedQuestion?";
        url = url + "userId=" + userId;
        url = url + "&questionId=" + questionId;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(""))
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

    public static void ban(long roomId, long userId) {
        String elevatedPassword = MainModCommunication.getAdminPassword(userId);
        String url = "http://localhost:8080/api/v2/rooms/ban?";
        url = url + "userId=" + userId;
        url= url + "&elevatedPassword=" + elevatedPassword;
        url = url + "&roomId=" + roomId;

        HttpRequest request = HttpRequest.newBuilder().
                PUT(HttpRequest.BodyPublishers.ofString(gson.toJson(""))).
                uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
    }

    public static void delete(long roomId, long questionId) {

        String url = "http://localhost:8080/api/v2/questions/deleteOne?";
        url = url + "questionId=" + questionId;
        url = url + "&roomId=" + roomId;

        HttpRequest request = HttpRequest.newBuilder().DELETE().uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();

        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }

    }
}
