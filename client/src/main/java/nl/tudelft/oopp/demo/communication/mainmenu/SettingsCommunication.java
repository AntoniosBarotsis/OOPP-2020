package nl.tudelft.oopp.demo.communication.mainmenu;

public class SettingsCommunication extends  MainMenuCommunication{
    private static final String url = "http://localhost:8080/api/v2/";

    /**
     * Request student code for a room.
     * @param id id of a room
     * @return student code for a chosen room
     */
    public static String getStudentPassword(long id) {
        String link = url + "rooms/public?roomId=";
        return  requestStringData(link + id);
    }

    /**
     * Request moderator code for a room.
     * @param id id of a room
     * @return moderator code for a chosen room
     */
    public static String getAdminPassword(long id) {
        String link = url + "rooms/private?";
        link = link + "roomId=" + id;
        return  requestStringData(link);
    }
}
