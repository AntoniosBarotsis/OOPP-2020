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
        DateFormat dateFormat = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss");
        String strDate = dateFormat.format(value.getTimeCreated());

        gen.writeStartObject();
        gen.writeNumberField("id", value.getId());
        gen.writeStringField("text", value.getText());
        gen.writeStringField("answer", value.getAnswer());
        gen.writeStringField("timeCreated", strDate);

        // Author
        gen.writeFieldName("author");
        gen.writeStartObject();
        gen.writeNumberField("id", value.getAuthor().getId());
        gen.writeStringField("username", value.getAuthor().getUsername());
        gen.writeStringField("ip", value.getAuthor().getIp());
        gen.writeStringField("TYPE", value.getAuthor().typeToString());
        gen.writeEndObject();

        gen.writeEndObject();
    }
}