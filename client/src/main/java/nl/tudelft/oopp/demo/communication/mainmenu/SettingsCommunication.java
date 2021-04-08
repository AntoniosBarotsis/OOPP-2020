package nl.tudelft.oopp.demo.communication.mainmenu;

import nl.tudelft.oopp.demo.data.RoomConfig;

public class SettingsCommunication extends  MainMenuCommunication {
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

    /**
     * Save settings of room.
     * @param roomId id of a room
     * @param userId id of a user
     * @param roomConfig new settings
     */
    public static String saveSettings(long roomId, long userId, RoomConfig roomConfig) {
        String link = url + "rooms/setConfig?";
        link = link + "roomId=" + roomId;
        link = link + "&userId=" + userId;
        return sendPutRequestRoomConfig(link, roomConfig);
    }

    /**
     * Delete all questions in a room.
     * @param roomId id of room
     * @param userId id of user
     */
    public static String deleteQuestions(long roomId, long userId) {
        String link = url + "rooms/deleteQuestions?";
        link = link + "roomId=" + roomId;
        link = link + "&userId=" + userId;
        return sendDeleteRequest(link);
    }
}
