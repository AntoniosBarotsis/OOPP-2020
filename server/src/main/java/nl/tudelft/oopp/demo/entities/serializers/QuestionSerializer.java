package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import nl.tudelft.oopp.demo.entities.Question;



/**
 * The type Question serializer. This limits the Question files to
 * text, answer and timeCreated
 */
public class QuestionSerializer extends StdSerializer<Question> {

    /**
     * Instantiates a new Question serializer.
     */
    public QuestionSerializer() {
        this(null);
    }

    /**
     * Instantiates a new Question serializer.
     *
     * @param t the t
     */
    protected QuestionSerializer(Class<Question> t) {
        super(t);
    }

    @Override
    public void serialize(Question value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("text", value.getText());
        gen.writeStringField("answer", value.getAnswer());
        gen.writeNumberField("upvotes", value.getUpvotes());
        gen.writeNumberField("score", value.getScore());
        gen.writeStringField("timeCreated", value.getTimeCreated().toString());
        gen.writeStringField("QuestionStatus", value.statusToString());
        gen.writeBooleanField("BeingAnswered", value.isBeingAnswered());

        // Author
        gen.writeFieldName("author");
        gen.writeStartObject();
        gen.writeNumberField("id", value.getAuthor().getId());
        gen.writeStringField("username", value.getAuthor().getUsername());
        gen.writeEndObject();

        gen.writeEndObject();
    }
}