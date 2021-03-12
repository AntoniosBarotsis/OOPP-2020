package nl.tudelft.oopp.demo.communication.mainmenu;

public class MainModCommunication extends MainMenuCommunication {
    /**
     * Request student code for a room.
     * @param id id of a room
     * @return student code for a chosen room
     */
    public static String getStudentPassword(long id) {
        String link = "http://localhost:8080/api/v1/rooms/public/";
        return  requestStringData(link + id);
    }

    /**
     * Request moderator code for a room.
     * @param id id of a room
     * @return moderator code for a chosen room
     */
    public static String getAdminPassword(long id) {
        String link = "http://localhost:8080/api/v1/rooms/private/";
        return  requestStringData(link + id);
    }

}
