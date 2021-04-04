package nl.tudelft.oopp.demo.communication.questionview;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import nl.tudelft.oopp.demo.communication.mainmenu.SettingsCommunication;
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
                .DELETE()
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
     * Bans the user.
     *
     * @param roomId the room id
     * @param modId the moderator id
     * @param authorId the id of the author
     */
    public static void ban(long roomId, long modId, long authorId) {
        String elevatedPassword;
        try {
            elevatedPassword = SettingsCommunication.getAdminPassword(roomId);
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }
        
        
        String url = "http://localhost:8080/api/v2/rooms/ban?";
        url = url + "userId=" + modId;
        url = url + "&elevatedPassword=" + elevatedPassword;
        url = url + "&roomId=" + roomId;
        url = url + "&idToBeBanned=" + authorId;

        if (!isBanned(roomId, authorId)) {
            HttpRequest request = HttpRequest.newBuilder()
                    .PUT(HttpRequest.BodyPublishers.ofString(gson.toJson("")))
                    .uri(URI.create(url)).build();
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

    /**
     * Returns wether a user is banned or not.
     *
     * @param roomId the room id.
     * @param authorId the id of the user.
     * @return boolean of is the user banned.
     */
    public static boolean isBanned(Long roomId, Long authorId) {
        String url = "http://localhost:8080/api/v2/rooms/isBanned?";
        url = url + "id=" + authorId;
        url = url + "&roomId=" + roomId;

        HttpRequest request = HttpRequest.newBuilder()
                .GET()
                .uri(URI.create(url)).build();
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Boolean(null);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
            System.out.println(response.body());
        }
        return Boolean.parseBoolean(response.body());

    }

    /**\
     * Deletes the question from the database.
     *
     * @param roomId the room id.
     * @param questionId the question id.
     */
    public static void delete(long roomId, long questionId) {

        String url = "http://localhost:8080/api/v2/questions/deleteOne?";
        url = url + "questionId=" + questionId;
        url = url + "&roomId=" + roomId;

        HttpRequest request = HttpRequest
                .newBuilder()
                .DELETE()
                .uri(URI.create(url))
                .build();

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

    /**
     * Sets a question to true or false depending on
     * whether it is being modified by the moderators.
     *
     * @param id the question id
     * @param b the status of the field
     */
    public static void setBeingAnswered(long id, boolean b) {
        String url = "http://localhost:8080/api/v2/questions/setBeingAnswered?";
        url = url + "questionId=" + id;
        url = url + "&status=" + b;

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(URI.create(url))
                .header("Content-Type", "application/json")
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
     * Retrieves the BeingAnswered boolean field from a question.
     *
     * @param id the id of the question
     * @return whether a question is being answered
     */
    public static boolean getBeingAnswered(long id) {
        String url = "http://localhost:8080/api/v2/questions/getBeingAnswered?";
        url = url + "questionId=" + id;

        HttpRequest request = HttpRequest
                .newBuilder()
                .GET()
                .uri(URI.create(url))
                .build();

        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return new Boolean(null);
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return Boolean.parseBoolean(response.body());
    }
}
