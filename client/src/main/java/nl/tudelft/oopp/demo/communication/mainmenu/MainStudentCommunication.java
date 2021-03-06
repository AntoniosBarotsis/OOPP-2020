package nl.tudelft.oopp.demo.communication.mainmenu;

import nl.tudelft.oopp.demo.data.helper.QuestionHelper;

public class MainStudentCommunication extends MainMenuCommunication {
    private static final String url = "http://localhost:8080/api/v2/";

    /**
     * Increases tooFast counter for room.
     * @param id id of room
     */
    public static void increaseTooFast(long id) {
        String link = url + "rooms/tooFast/increment?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Increases tooSlow counter for room.
     * @param id id of room
     */
    public static void increaseTooSlow(long id) {
        String link = url + "rooms/tooSlow/increment?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Increases normal pace counter for room.
     * @param id id of room
     */
    public static void increaseNormal(long id) {
        String link = url + "rooms/normalSpeed/increment?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Decreases tooFast counter for room.
     * @param id id of room
     */
    public static void decreaseTooFast(long id) {
        String link = url + "rooms/tooFast/decrement?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Decreases tooSlow counter for room.
     * @param id id of room
     */
    public static void decreaseTooSlow(long id) {
        String link = url + "rooms/tooSlow/decrement?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Decreases normal pace counter for room.
     * @param id id of room
     */
    public static void decreaseNormal(long id) {
        String link = url + "rooms/normalSpeed/decrement?roomId=";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Send a new question to server.
     * @param roomId id of room
     * @param userId id of user
     */
    public static String sendQuestion(long roomId, long userId, QuestionHelper question) {
        String link = url + "questions/add?";
        link = link + "roomId=" + roomId;
        link = link + "&authorId=" + userId;
        return sendPostRequest(link, question);
    }
}
