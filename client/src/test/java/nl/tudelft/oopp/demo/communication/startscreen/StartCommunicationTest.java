package nl.tudelft.oopp.demo.communication.startscreen;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.helper.RoomHelper;
import org.junit.jupiter.api.Test;

public class StartCommunicationTest {

    @Test
    void createRoomTest() {
        RoomHelper roomHelper = new RoomHelper("title", "username",
                true, new RoomConfig(5,5,300,300),
                "startDate", "endDate");
        assertNotNull(StartCommunication.createRoom(roomHelper));
    }

    @Test
    void joinRoom() {
        assertDoesNotThrow(() -> StartCommunication.joinRoom("code", "username"));
    }

    @Test
    void getRoom() {
        assertDoesNotThrow(() -> StartCommunication.getRoom("code"));
    }

}
