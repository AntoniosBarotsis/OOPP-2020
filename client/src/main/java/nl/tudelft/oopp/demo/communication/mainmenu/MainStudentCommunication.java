package nl.tudelft.oopp.demo.communication.mainmenu;

public class MainStudentCommunication extends MainMenuCommunication {
    /**
     * Increases tooFast counter for room.
     * @param id id of room
     */
    public static void increaseTooFast(long id) {
        String link = "http://localhost:8080/api/v1/rooms/tooFast/increment/";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Increases tooSlow counter for room.
     * @param id id of room
     */
    public static void increaseTooSlow(long id) {
        String link = "http://localhost:8080/api/v1/rooms/tooSlow/increment/";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Decreases tooFast counter for room.
     * @param id id of room
     */
    public static void decreaseTooFast(long id) {
        String link = "http://localhost:8080/api/v1/rooms/tooFast/decrement/";
        sendEmptyPutRequest(link + id);
    }

    /**
     * Decreases tooSlow counter for room.
     * @param id id of room
     */
    public static void decreaseTooSlow(long id) {
        String link = "http://localhost:8080/api/v1/rooms/tooSlow/decrement/";
        sendEmptyPutRequest(link + id);
    }
}
