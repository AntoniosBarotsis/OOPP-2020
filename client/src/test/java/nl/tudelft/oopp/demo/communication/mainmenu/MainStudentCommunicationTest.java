package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertEquals;

import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;
import org.junit.jupiter.api.Test;

class MainStudentCommunicationTest {

    @Test
    void increaseTooFast() {
        int currentTooFast = MainModCommunication.getRoom(1).getTooFast();
        MainStudentCommunication.increaseTooFast(1);

        // The test will give adequate results only if the server is running.
        if (currentTooFast != -1) {
            assertEquals(currentTooFast + 1, MainModCommunication.getRoom(1).getTooFast());
        }
    }

    @Test
    void increaseTooSlow() {
        int currentTooSlow = MainModCommunication.getRoom(1).getTooSlow();
        MainStudentCommunication.increaseTooSlow(1);

        // The test will give adequate results only if the server is running.
        if (currentTooSlow != -1) {
            assertEquals(currentTooSlow + 1, MainModCommunication.getRoom(1).getTooSlow());
        }
    }

    @Test
    void decreaseTooFast() {
        int currentTooFast = MainModCommunication.getRoom(1).getTooFast();
        MainStudentCommunication.decreaseTooFast(1);

        // The test will give adequate results only if the server is running.
        if (currentTooFast != -1) {
            assertEquals(currentTooFast - 1, MainModCommunication.getRoom(1).getTooFast());
        }
    }

    @Test
    void decreaseTooSlow() {
        int currentTooSlow = MainModCommunication.getRoom(1).getTooSlow();
        MainStudentCommunication.decreaseTooSlow(1);

        // The test will give adequate results only if the server is running.
        if (currentTooSlow != -1) {
            assertEquals(currentTooSlow - 1, MainModCommunication.getRoom(1).getTooSlow());
        }
    }

    @Test
    void sendQuestion() {
        QuestionHelper question = new QuestionHelper("text", new StudentHelper("Student", "ip"));
        assertDoesNotThrow(() -> MainStudentCommunication.sendQuestion(1,3, question));
    }
}