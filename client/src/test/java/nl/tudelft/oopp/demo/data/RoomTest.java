package nl.tudelft.oopp.demo.data;

import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.*;

class RoomTest {

    private Room room = new Room(42, "OOPP lecture 1", new Date(1614969845), true, 5, 1);

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
}