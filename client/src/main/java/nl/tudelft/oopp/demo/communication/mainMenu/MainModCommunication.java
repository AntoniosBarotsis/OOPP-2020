package nl.tudelft.oopp.demo.communication.mainMenu;

import com.google.gson.Gson;

import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class MainModCommunication {
    private static HttpClient client = HttpClient.newBuilder().build();

    private static Gson gson = new Gson();

    /**
     * Request student code for room.
     * @param id id of room
     * @return student code for chosen room
     */
    public static String getStudentPassword(long id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api/v1/rooms/public/" + id)).build();
        return  requestStringData(request);
    }

    /**
     * Request moderator code for room.
     * @param id id of room
     * @return moderator code for chosen room
     */
    public static String getAdminPassword(long id) {
        HttpRequest request = HttpRequest.newBuilder().GET().uri(URI.create("http://localhost:8080/api/v1/rooms/private/" + id)).build();
        return  requestStringData(request);
    }

    /**
     * Request string data from server.
     * @param request httprequest to be made
     * @return response body of request made
     */
    public static String requestStringData(HttpRequest request){
        HttpResponse<String> response = null;
        try {
            response = client.send(request, HttpResponse.BodyHandlers.ofString());
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
        if (response.statusCode() != 200) {
            System.out.println("Status: " + response.statusCode());
        }
        return response.body();
    }
}
