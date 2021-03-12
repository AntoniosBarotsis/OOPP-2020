package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MainMenuCommunicationTest {

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
        String link = "http://localhost:8080/api/v1/rooms/1/";
        assertNotNull(MainMenuCommunication.requestStringData(link));
    }

    @Test
    void sendEmptyPutRequest() {
        String link = "http://localhost:8080/api/v1/rooms/tooFast/increment/";
        assertDoesNotThrow(() -> MainMenuCommunication.sendEmptyPutRequest(link));
    }
}