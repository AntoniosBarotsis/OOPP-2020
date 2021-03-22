package nl.tudelft.oopp.demo.communication.startscreen;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

public class StartCommunicationTest {

    @Test
    void createRoomTest() {
        assertNotNull(StartCommunication.createRoom("TestRoom"));
    }

    /*
      Does not work since I need an actual room password to join one
      Should work once that has been implemented
    @Test
    void joinRoomTest() {
        assertNotNull(StartCommunication.joinRoom("1", "TestName"));
    }
    */

    /*
     Does not work since Date's .toString() inserts spaces.
     Either a new date constructor or reader.
     */
    @Test
    void createScheduledRoomTest() {
        assertNotNull(StartCommunication.createScheduledRoom("TestRoom", new Date()));
    }

}
