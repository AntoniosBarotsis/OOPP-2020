package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.entities.Poll;

public class PollSerializer extends StdSerializer<Poll> {

    public PollSerializer() {
        this(null);
    }

    public PollSerializer(Class<Poll> t) {
        super(t);
    }

    @Override
    public void serialize(Poll value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {
        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");

        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeStringField("title", value.getTitle());
        gen.writeStringField("text", value.getText());
        gen.writeStringField("status", value.getStatus().toString());
        gen.writeStringField("timeCreated", dateFormat.format(value.getTimeCreated()));

        gen.writeArrayFieldStart("options");
        for (String option : value.getOptions()) {
            gen.writeString(option);
        }
        gen.writeEndArray();

        gen.writeArrayFieldStart("correctAnswer");
        for (String correct : value.getCorrectAnswer()) {
            gen.writeString(correct);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
