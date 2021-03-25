package nl.tudelft.oopp.demo.entities.serializers;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import java.io.IOException;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.log.LogBan;
import nl.tudelft.oopp.demo.entities.log.LogCollection;
import nl.tudelft.oopp.demo.entities.log.LogJoin;
import nl.tudelft.oopp.demo.entities.log.LogQuestion;

public class LogCollectionSerializer extends StdSerializer<LogCollection> {
    public LogCollectionSerializer() {
        this(null);
    }

    public LogCollectionSerializer(Class<LogCollection> t) {
        super(t);
    }

    @Override
    public void serialize(LogCollection value, JsonGenerator gen, SerializerProvider provider)
        throws IOException {
        gen.writeStartObject();

        gen.writeFieldName("bans");
        gen.writeStartArray();
        for (LogBan ban : value.getBans()) {
            gen.writeStartObject();
            gen.writeStringField("userIp", ban.getUser().getIp());
            gen.writeStringField("action", ban.getAction().toString());
            gen.writeStringField("date", ban.getDate().toString());
            gen.writeStringField("username", ban.getUser().getUsername());
            gen.writeStringField("ip", ban.getUser().getIp());
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeFieldName("joins");
        gen.writeStartArray();
        for (LogJoin join : value.getJoins()) {
            gen.writeStartObject();
            gen.writeStringField("userIp", join.getUser().getIp());
            gen.writeStringField("action", join.getAction().toString());
            gen.writeStringField("date", join.getDate().toString());
            gen.writeStringField("username", join.getUser().getUsername());
            gen.writeNumberField("room", join.getRoom().getId());
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeFieldName("questions");
        gen.writeStartArray();
        for (LogQuestion question : value.getQuestions()) {
            gen.writeStartObject();
            gen.writeStringField("userIp", question.getUser().getIp());
            gen.writeStringField("action", question.getAction().toString());
            gen.writeStringField("date", question.getDate().toString());
            gen.writeStringField("username", question.getUser().getUsername());
            gen.writeNumberField("question", question.getQuestion().getId());
            gen.writeEndObject();
        }
        gen.writeEndArray();

        gen.writeEndObject();
    }
}
