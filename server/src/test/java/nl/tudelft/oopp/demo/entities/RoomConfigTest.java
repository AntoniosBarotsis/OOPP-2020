package nl.tudelft.oopp.demo.entities;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomConfigTest {
    RoomConfig a;
    RoomConfig a2;
    RoomConfig b;

    @BeforeEach
    void setUp() {
        a = new RoomConfig();
        a2 = new RoomConfig();
        b = new RoomConfig();
    }

    @Test
    void getId() {
        assertThat(a.getId()).isNotNull();
        assertThat(b.getId()).isEqualTo(a.getId());
    }

    @Test
    void getStudentRefreshRate() {
        assertThat(a.getStudentRefreshRate()).isEqualTo(5);
    }

    @Test
    void getModRefreshRate() {
        assertThat(a.getModRefreshRate()).isEqualTo(5);
    }

    @Test
    void getQuestionCooldown() {
        assertThat(a.getQuestionCooldown()).isEqualTo(300);
    }

    @Test
    void getPaceCooldown() {
        assertThat(a.getPaceCooldown()).isEqualTo(300);
    }

    @Test
    void setId() {
        a.setId(1);
        assertThat(a.getId()).isEqualTo(1);
    }

    @Test
    void setStudentRefreshRate() {
        a.setStudentRefreshRate(200);
        assertThat(a.getStudentRefreshRate()).isEqualTo(200);
    }

    @Test
    void setModRefreshRate() {
        a.setModRefreshRate(200);
        assertThat(a.getModRefreshRate()).isEqualTo(200);
    }

    @Test
    void setQuestionCooldown() {
        a.setQuestionCooldown(10);
        assertThat(a.getQuestionCooldown()).isEqualTo(10);
    }

    @Test
    void setPaceCooldown() {
        a.setPaceCooldown(20);
        assertThat(a.getPaceCooldown()).isEqualTo(20);
    }

    @Test
    void testEquals() {
        assertThat(a).isEqualTo(a2);
    }

    @Test
    void testToString() {
        String expected = "RoomConfig(id=0, studentRefreshRate=5, modRefreshRate=5, questionCooldown=300, paceCooldown=300)";
        assertThat(a.toString()).isEqualTo(expected);
    }
}