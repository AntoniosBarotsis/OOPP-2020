package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.entities.Room;

public class RoomSerializer extends StdSerializer<Room> {
    public RoomSerializer() {
        this(null);
    }

    public RoomSerializer(Class<Room> t) {
        super(t);
    }

    @Override
    public void serialize(Room value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("timeCreated", dateFormat.format(value.getStartingDate()));
        gen.writeBooleanField("repeatingLecture", value.isRepeatingLecture());
        gen.writeNumberField("tooFast", value.getTooFast());
        gen.writeNumberField("tooSlow", value.getTooSlow());
        gen.writeNumberField("normalSpeed", value.getNormalSpeed());
        gen.writeBooleanField("isOngoing", value.isOngoing());
        gen.writeEndObject();
    }
}
