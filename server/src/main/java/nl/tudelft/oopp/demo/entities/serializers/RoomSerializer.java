package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;

import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;

public class RoomSerializer extends StdSerializer<Room> {
    @Autowired
    private RoomService roomService;

    public RoomSerializer() {
        this(null);
    }

    public RoomSerializer(Class<Room> t) {
        super(t);
    }

    @Override
    public void serialize(Room value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        roomService.refreshOngoing(value.getId());

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("timeCreated", dateFormat.format(value.getTimeCreated()));
        gen.writeStringField("startingDate", dateFormat.format(value.getStartingDate()));
        gen.writeStringField("endingDate", dateFormat.format(value.getEndingDate()));
        gen.writeBooleanField("repeatingLecture", value.isRepeatingLecture());
        gen.writeNumberField("tooFast", value.getTooFast());
        gen.writeNumberField("tooSlow", value.getTooSlow());
        gen.writeNumberField("normalSpeed", value.getNormalSpeed());
        gen.writeBooleanField("isOngoing", value.isOngoing());

        gen.writeFieldName("roomConfig");
        gen.writeStartObject();
        gen.writeNumberField("studentRefreshRate", value.getRoomConfig().getStudentRefreshRate());
        gen.writeNumberField("modRefreshRate", value.getRoomConfig().getModRefreshRate());
        gen.writeNumberField("questionCooldown", value.getRoomConfig().getQuestionCooldown());
        gen.writeNumberField("paceCooldown", value.getRoomConfig().getPaceCooldown());
        gen.writeEndObject();

        gen.writeArrayFieldStart("poll_ids");
        for (Poll poll : value.getPolls()) {
            gen.writeNumber(poll.getId());
        }
        gen.writeEndArray();

        gen.writeNumberField("admin_id", value.getAdmin());
        gen.writeStringField("normal_password", value.getNormalPassword());
        gen.writeEndObject();
    }
}
