package nl.tudelft.oopp.demo.data.helper;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import nl.tudelft.oopp.demo.data.RoomConfig;
import org.junit.jupiter.api.Test;


class RoomHelperTest {

    private RoomConfig roomConfig = new RoomConfig(2,2,
            300, 300);
    private RoomHelper roomHelper = new RoomHelper("title", "username",
            true, roomConfig,
            "18-10-2021 16:00", "18-10-2021 18:00");

    @Test
    void getTitle() {
        assertEquals("title", roomHelper.getTitle());
    }

    @Test
    void setTitle() {
        roomHelper.setTitle("new title");
        assertEquals("new title", roomHelper.getTitle());
    }

    @Test
    void getUsername() {
        assertEquals("username", roomHelper.getUsername());
    }

    @Test
    void setUsername() {
        roomHelper.setUsername("new username");
        assertEquals("new username", roomHelper.getUsername());
    }

    @Test
    void isRepeatingLecture() {
        assertTrue(roomHelper.isRepeatingLecture());
    }

    @Test
    void setRepeatingLecture() {
        roomHelper.setRepeatingLecture(false);
        assertFalse(roomHelper.isRepeatingLecture());
    }

    @Test
    void getRoomConfig() {
        assertEquals(roomConfig, roomHelper.getRoomConfig());
    }

    @Test
    void setRoomConfig() {
        RoomConfig roomConfig2 = new RoomConfig(2,2,
                300, 300);
        roomHelper.setRoomConfig(roomConfig2);
        assertEquals(roomConfig2, roomHelper.getRoomConfig());
    }

    @Test
    void getStartingDate() {
        assertEquals("18-10-2021 16:00", roomHelper.getStartingDate());
    }

    @Test
    void setStartingDate() {
        roomHelper.setStartingDate("18-10-2021 13:00");
        assertEquals("18-10-2021 13:00", roomHelper.getStartingDate());
    }

    @Test
    void getEndingDate() {
        assertEquals("18-10-2021 18:00", roomHelper.getEndingDate());
    }

    @Test
    void setEndingDate() {
        roomHelper.setEndingDate("18-10-2021 15:00");
        assertEquals("18-10-2021 15:00", roomHelper.getEndingDate());
    }
}