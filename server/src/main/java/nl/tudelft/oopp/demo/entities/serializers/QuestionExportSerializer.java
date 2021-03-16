package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.entities.Question;


/**
 * The type Question serializer. This limits the Question files to
 * text, answer and timeCreated
 */
public class QuestionExportSerializer extends StdSerializer<Question> {

    /**
     * Instantiates a new Question serializer.
     */
    public QuestionExportSerializer() {
        this(null);
    }

    /**
     * Instantiates a new Question serializer.
     *
     * @param t the t
     */
    protected QuestionExportSerializer(Class<Question> t) {
        super(t);
    }

    @Override
    public void serialize(Question value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {

        gen.writeStartObject();
        gen.writeStringField("text", value.getText());
        gen.writeStringField("answer", value.getAnswer());
        gen.writeStringField("timeCreated", value.getTimeCreated().toString());
        gen.writeEndObject();
    }
}