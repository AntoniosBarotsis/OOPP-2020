package nl.tudelft.oopp.demo.entities.helpers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class RoomHelperTest {

    RoomHelper a1;
    Room a2;
    RoomHelper b1;
    RoomHelper c1;
    ElevatedUser admin;
    RoomConfig settings;

    Date time;
    Date endTime;

    @BeforeEach
    void setUp() {
        time = new Date();
        admin = new ElevatedUser("username", "ip", true);
        settings = new RoomConfig();
        endTime = new Date();

        a1 = new RoomHelper("Title", "username", false, time, null);
        b1 = new RoomHelper("Title", "username", false, time, null);
        c1 = new RoomHelper("Title", "username", false, settings, time, endTime);
        a2 = new Room("Title", false, admin);

    }

    @Test
    void createRoom() {
        assertDoesNotThrow(() -> a1.createRoom(admin));
        assertThat(a1.createRoom(admin)).isNotNull();
    }

    @Test
    void getTitle() {
        assertThat(a1.getTitle()).isEqualTo("Title");
    }

    @Test
    void getUsername() {
        assertThat(a1.getUsername()).isEqualTo("username");
    }

    @Test
    void isRepeatingLecture() {
        assertThat(a1.isRepeatingLecture()).isFalse();
    }

    @Test
    void getRoomConfig() {
        assertThat(a1.getRoomConfig()).isNull();
        assertThat(c1.getRoomConfig()).isEqualTo(settings);
    }

    @Test
    void getStartingDate() {
        assertThat(a1.getStartingDate()).isEqualTo(time);
    }

    @Test
    void getEndingDate() {
        assertThat(c1.getEndingDate()).isEqualTo(endTime);
    }

    @Test
    void setTitle() {
        String expected = "New Title";
        a1.setTitle(expected);

        assertThat(a1.getTitle()).isEqualTo(expected);
    }

    @Test
    void setUsername() {
        String expected = "New user";
        a1.setUsername(expected);

        assertThat(a1.getUsername()).isEqualTo(expected);
    }

    @Test
    void setRepeatingLecture() {
        boolean expected = true;
        a1.setRepeatingLecture(expected);

        assertThat(a1.isRepeatingLecture()).isEqualTo(true);
    }

    @Test
    void setRoomConfig() {
        RoomConfig newsettings = new RoomConfig();
        newsettings.setPaceCooldown(2);
        newsettings.setQuestionCooldown(2);
        newsettings.setModRefreshRate(2);
        newsettings.setStudentRefreshRate(2);

        a1.setRoomConfig(newsettings);

        assertThat(a1.getRoomConfig()).isEqualTo(newsettings);
    }

    @Test
    void setStartingDate() {
        Date newDate = new Date();
        a1.setStartingDate(newDate);

        assertThat(a1.getStartingDate()).isEqualTo(newDate);
    }

    @Test
    void setEndingDate() {
        Date newDate = new Date();
        a1.setEndingDate(newDate);

        assertThat(a1.getEndingDate()).isEqualTo(newDate);
    }

    @Test
    void testEquals() {
        assertThat(a1).isEqualTo(b1);
    }

    @Test
    void testHashCode() {
        assertThat(a1.hashCode()).isEqualTo(b1.hashCode());
    }

    @Test
    void testToString() {
        String expected = "RoomHelper(title=Title, username=username, repeatingLecture=false, "
                + "roomConfig=null, startingDate=" + a1.getStartingDate() + ", endingDate=null)";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}