package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type User serializer.
 */
public class UserSerializer extends StdSerializer<User> {

    public UserSerializer() {
        this(null);
    }

    public UserSerializer(Class<User> t) {
        super(t);
    }

    @Override
    public void serialize(User value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("username", value.getUsername());
        gen.writeStringField("ip", value.getIp());
        gen.writeStringField("userType", value.typeToString());

        // Asked Questions
        gen.writeFieldName("questionsAsked");
        gen.writeStartArray();
        for (Question question : value.getQuestionsAsked()) {
            gen.writeNumber(question.getId());
        }
        gen.writeEndArray();

        // Upvoted questions
        gen.writeFieldName("questionsUpvoted");
        gen.writeStartArray();
        for (Question question : value.getQuestionsUpvoted()) {
            gen.writeNumber(question.getId());
        }
        gen.writeEndArray();
        gen.writeEndObject();
    }
}
