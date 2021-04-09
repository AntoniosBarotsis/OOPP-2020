package nl.tudelft.oopp.demo.entities;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomConfigTest {
    RoomConfig a1;
    RoomConfig a2;
    RoomConfig b1;

    @BeforeEach
    void setUp() {
        a1 = new RoomConfig();
        a2 = new RoomConfig();
        b1 = new RoomConfig();
    }

    @Test
    void getId() {
        assertThat(a1.getId()).isNotNull();
        assertThat(b1.getId()).isEqualTo(a1.getId());
    }

    @Test
    void getStudentRefreshRate() {
        assertThat(a1.getStudentRefreshRate()).isEqualTo(5);
    }

    @Test
    void getModRefreshRate() {
        assertThat(a1.getModRefreshRate()).isEqualTo(5);
    }

    @Test
    void getQuestionCooldown() {
        assertThat(a1.getQuestionCooldown()).isEqualTo(300);
    }

    @Test
    void getPaceCooldown() {
        assertThat(a1.getPaceCooldown()).isEqualTo(300);
    }

    @Test
    void setId() {
        a1.setId(1);
        assertThat(a1.getId()).isEqualTo(1);
    }

    @Test
    void setStudentRefreshRate() {
        a1.setStudentRefreshRate(200);
        assertThat(a1.getStudentRefreshRate()).isEqualTo(200);
    }

    @Test
    void setModRefreshRate() {
        a1.setModRefreshRate(200);
        assertThat(a1.getModRefreshRate()).isEqualTo(200);
    }

    @Test
    void setQuestionCooldown() {
        a1.setQuestionCooldown(10);
        assertThat(a1.getQuestionCooldown()).isEqualTo(10);
    }

    @Test
    void setPaceCooldown() {
        a1.setPaceCooldown(20);
        assertThat(a1.getPaceCooldown()).isEqualTo(20);
    }

    @Test
    void testEquals() {
        assertThat(a1).isEqualTo(a2);
    }

    @Test
    void testToString() {
        String expected = "RoomConfig(id=0, studentRefreshRate=5, "
                + "modRefreshRate=5, questionCooldown=300, paceCooldown=300)";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}