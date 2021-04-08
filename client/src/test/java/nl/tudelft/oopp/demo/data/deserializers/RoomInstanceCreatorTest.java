package nl.tudelft.oopp.demo.data.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.data.Room;
import org.junit.jupiter.api.Test;

class RoomInstanceCreatorTest {

    private static Gson gson = new Gson();

    @Test
    void testDeserialization() {
        String json = "{\"id\":1775473732367787483,\"title\":\"Room Title\","
                + "\"timeCreated\":\"2021-04-06 05:41:33\","
                + "\"startingDate\":\"2021-05-06 05:41:33\","
                + "\"endingDate\":\"2021-06-06 05:41:33\","
                + "\"repeatingLecture\":false,\"tooFast\":10,\"tooSlow\":20,\"normalSpeed\":30,"
                + "\"isOngoing\":true,\"roomConfig\":{\"studentRefreshRate\":15,"
                + "\"modRefreshRate\":25,\"questionCooldown\":3030,\"paceCooldown\":3040},"
                + "\"admin_id\":5120336323440220676,\"normal_password\":"
                + "\"b0af6d42a1b05153\"}";

        Room room = gson.fromJson(json, Room.class);
        assertEquals("Room Title", room.getTitle());
        assertEquals(1775473732367787483L, room.getId());
        assertEquals("2021-05-06 05:41:33",
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").format(room.getStartingDate()));
        assertFalse(room.isRepeatingLecture());
        assertTrue(room.isOngoing());
        assertEquals(10, room.getTooFast());
        assertEquals(20, room.getTooSlow());
        assertEquals(30, room.getNormalSpeed());
        assertEquals(15, room.getSettings().getStudentRefreshRate());
        assertEquals(25, room.getSettings().getModRefreshRate());
        assertEquals(3030, room.getSettings().getQuestionCooldown());
        assertEquals(3040, room.getSettings().getPaceCooldown());
    }

}