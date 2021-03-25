package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Date;

import org.junit.jupiter.api.Test;

class RoomTest {

    private RoomConfig settings = new RoomConfig(2,3,4,5);
    private Room room = new Room(42, "OOPP lecture 1",
        new Date(1614969845), true, 5, 1, 3, true, settings);

    @Test
    void constructor() {
        assertNotNull(room);
    }

    @Test
    void getId() {
        assertEquals(42, room.getId());
    }

    @Test
    void getTitle() {
        assertEquals("OOPP lecture 1", room.getTitle());
    }

    @Test
    void getStartingDate() {
        assertEquals(new Date(1614969845), room.getStartingDate());
    }

    @Test
    void setStartingDate() {
        room.setStartingDate(new Date(1614969945));
        assertEquals(new Date(1614969945), room.getStartingDate());
    }

    @Test
    void isRepeatingLecture() {
        assertTrue(room.isRepeatingLecture());
    }

    @Test
    void setRepeatingLecture() {
        room.setRepeatingLecture(false);
        assertFalse(room.isRepeatingLecture());
    }

    @Test
    void getTooFast() {
        assertEquals(5, room.getTooFast());
    }

    @Test
    void setTooFast() {
        room.setTooFast(4);
        assertEquals(4, room.getTooFast());
    }

    @Test
    void getTooSlow() {
        assertEquals(1, room.getTooSlow());
    }

    @Test
    void setTooSlow() {
        room.setTooSlow(2);
        assertEquals(2, room.getTooSlow());
    }

    @Test
    void getNormalSpeed() {
        assertEquals(3, room.getNormalSpeed());
    }

    @Test
    void setNormalSpeed() {
        room.setNormalSpeed(15);
        assertEquals(15, room.getNormalSpeed());
    }

    @Test
    void isOngoing() {
        assertEquals(true, room.isOngoing());
    }

    @Test
    void setOngoing() {
        room.setOngoing(false);
        assertEquals(false, room.isOngoing());
    }

    @Test
    void getSettings() {
        assertEquals(settings, room.getSettings());
    }

    @Test
    void setSettings() {
        RoomConfig settings2 = new RoomConfig(567,583,894,578);
        room.setSettings(settings2);
        assertEquals(settings2, room.getSettings());
    }
}