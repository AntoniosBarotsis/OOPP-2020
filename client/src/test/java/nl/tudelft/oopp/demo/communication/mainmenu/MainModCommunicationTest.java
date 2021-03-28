package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MainModCommunicationTest {

    @Test
    void getAllQuestions() {
        assertNotNull(MainModCommunication.getAllQuestions(1));
    }

    @Test
    void getTopQuestions() {
        assertNotNull(MainModCommunication.getTopQuestions(1));
    }

    @Test
    void getAnsweredQuestions() {
        assertNotNull(MainModCommunication.getAnsweredQuestions(1));
    }

    @Test
    void getRoomLog() {
        assertNotNull(MainModCommunication.getRoomLog(1));
    }

    @Test
    void setOngoingLecture() {
        assertDoesNotThrow(() -> MainModCommunication.setOngoingLecture(1, false, 1));
    }
}