package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;
import org.junit.jupiter.api.Test;

class MainMenuCommunicationTest {
    private final String url = "http://localhost:8080/api/v2/";

    @Test
    void getQuestions() {
        assertNotNull(MainMenuCommunication.getQuestions(1));
    }

    @Test
    void getRoom() {
        assertNotNull(MainMenuCommunication.getRoom(1));
    }

    @Test
    void requestStringData() {
        String link = url + "rooms?id=1";
        assertNotNull(MainMenuCommunication.requestStringData(link));
    }

    @Test
    void sendEmptyPutRequest() {
        String link = url + "rooms/tooFast/increment?roomId=1";
        assertDoesNotThrow(() -> MainMenuCommunication.sendEmptyPutRequest(link));
    }

    @Test
    void sendPostRequest() {
        String link = url + "questions/add?roomId=1&authorId=3";
        QuestionHelper question = new QuestionHelper("test", new StudentHelper("Student", "ip"));
        assertDoesNotThrow(() -> MainMenuCommunication.sendPostRequest(link, question));
    }

    @Test
    void sendPutRequestRoomConfig() {
        String link = url + "rooms/setStudentRefreshRate?roomId=1&userId=1";
        assertNotNull(MainMenuCommunication.sendPutRequestRoomConfig(link,
                new RoomConfig(1,2,3,4)));
    }
}