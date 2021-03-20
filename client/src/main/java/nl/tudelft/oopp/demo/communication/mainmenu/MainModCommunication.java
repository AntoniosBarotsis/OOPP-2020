package nl.tudelft.oopp.demo.communication.mainmenu;

public class MainModCommunication extends MainMenuCommunication {
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
        link = link + "&ip=" + getIp();
        return  requestStringData(link);
    }

    /**
     * Request all questions for a chosen room.
     * @param id id of a room
     * @return all questions in json format
     */
    public static String getAllQuestions(long id) {
        String link = url + "questions/exportAll?roomId=";
        return  requestStringData(link + id);
    }

    /**
     * Request top 20 questions for a chosen room.
     * @param id id of a room
     * @return top 20 questions in json format
     */
    public static String getTopQuestions(long id) {
        String link = url + "questions/exportTop?amount=20&roomId=";
        return  requestStringData(link + id);
    }

    /**
     * Request all questions with text answer for a chosen room.
     * @param id id of a room
     * @return answered questions in json format
     */
    public static String getAnsweredQuestions(long id) {
        String link = url + "questions/exportAnswered?roomId=";
        return  requestStringData(link + id);
    }

}
