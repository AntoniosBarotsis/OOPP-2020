package nl.tudelft.oopp.demo.entities.helpers;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class RoomHelperTest {

    RoomHelper a;
    Room a2;
    RoomHelper b;
    RoomHelper c;
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

        a = new RoomHelper("Title", "username", false, time, null);
        b = new RoomHelper("Title", "username", false, time, null);
        c = new RoomHelper("Title", "username", false, settings, time, endTime);
        a2 = new Room("Title", false, admin);

    }

    @Test
    void createRoom() {
        assertDoesNotThrow(() -> a.createRoom(admin));
        assertThat(a.createRoom(admin)).isNotNull();
    }

    @Test
    void getTitle() {
        assertThat(a.getTitle()).isEqualTo("Title");
    }

    @Test
    void getUsername() {
        assertThat(a.getUsername()).isEqualTo("username");
    }

    @Test
    void isRepeatingLecture() {
        assertThat(a.isRepeatingLecture()).isFalse();
    }

    @Test
    void getRoomConfig() {
        assertThat(a.getRoomConfig()).isNull();
        assertThat(c.getRoomConfig()).isEqualTo(settings);
    }

    @Test
    void getStartingDate() {
        assertThat(a.getStartingDate()).isEqualTo(time);
    }

    @Test
    void getEndingDate() {
        assertThat(c.getEndingDate()).isEqualTo(endTime);
    }

    @Test
    void setTitle() {
        String expected = "New Title";
        a.setTitle(expected);

        assertThat(a.getTitle()).isEqualTo(expected);
    }

    @Test
    void setUsername() {
        String expected = "New user";
        a.setUsername(expected);

        assertThat(a.getUsername()).isEqualTo(expected);
    }

    @Test
    void setRepeatingLecture() {
        boolean expected = true;
        a.setRepeatingLecture(expected);

        assertThat(a.isRepeatingLecture()).isEqualTo(true);
    }

    @Test
    void setRoomConfig() {
        RoomConfig newsettings = new RoomConfig();
        newsettings.setPaceCooldown(2);
        newsettings.setQuestionCooldown(2);
        newsettings.setModRefreshRate(2);
        newsettings.setStudentRefreshRate(2);

        a.setRoomConfig(newsettings);

        assertThat(a.getRoomConfig()).isEqualTo(newsettings);
    }

    @Test
    void setStartingDate() {
        Date newDate = new Date();
        a.setStartingDate(newDate);

        assertThat(a.getStartingDate()).isEqualTo(newDate);
    }

    @Test
    void setEndingDate() {
        Date newDate = new Date();
        a.setEndingDate(newDate);

        assertThat(a.getEndingDate()).isEqualTo(newDate);
    }

    @Test
    void testEquals() {
        assertThat(a).isEqualTo(b);
    }

    @Test
    void testHashCode() {
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void testToString() {
        String expected = "RoomHelper(title=Title, username=username, repeatingLecture=false, " +
                "roomConfig=null, startingDate=" + a.getStartingDate() + ", endingDate=null)";
        assertThat(a.toString()).isEqualTo(expected);
    }
}