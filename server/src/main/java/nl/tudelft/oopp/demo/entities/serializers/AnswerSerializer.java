package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import nl.tudelft.oopp.demo.entities.Answer;

public class AnswerSerializer extends StdSerializer<Answer> {

    public AnswerSerializer() {
        this(null);
    }

    public AnswerSerializer(Class<Answer> t) {
        super(t);
    }

    @Override
    public void serialize(Answer value, JsonGenerator gen, SerializerProvider provider)
            throws IOException {

        gen.writeStartObject();

        gen.writeNumberField("id", value.getId());
        gen.writeNumberField("poll_id", value.getPollId());
        gen.writeNumberField("user_id", value.getUserId());

        gen.writeArrayFieldStart("answers");
        for (String answer : value.getAnswers()) {
            gen.writeString(answer);
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
