package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class RoomConfigTest {

    private RoomConfig settings = new RoomConfig(1,2,3,4);

    @Test
    void constructor() {
        assertNotNull(settings);
    }
    
    @Test
    void getStudentRefreshRate() {
        assertEquals(1, settings.getStudentRefreshRate());
    }

    @Test
    void setStudentRefreshRate() {
        settings.setStudentRefreshRate(200);
        assertEquals(200, settings.getStudentRefreshRate());
    }

    @Test
    void getModRefreshRate() {
        assertEquals(2, settings.getModRefreshRate());
    }

    @Test
    void setModRefreshRate() {
        settings.setModRefreshRate(300);
        assertEquals(300, settings.getModRefreshRate());
    }

    @Test
    void getQuestionCooldown() {
        assertEquals(3, settings.getQuestionCooldown());
    }

    @Test
    void setQuestionCooldown() {
        settings.setQuestionCooldown(400);
        assertEquals(400, settings.getQuestionCooldown());
    }

    @Test
    void getPaceCooldown() {
        assertEquals(4, settings.getPaceCooldown());
    }

    @Test
    void setPaceCooldown() {
        settings.setPaceCooldown(500);
        assertEquals(500, settings.getPaceCooldown());
    }
}