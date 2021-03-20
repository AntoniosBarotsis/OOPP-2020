package nl.tudelft.oopp.demo.data.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import nl.tudelft.oopp.demo.data.Room;

public class RoomInstanceCreator implements JsonDeserializer<Room> {

    /**
     * Custom deserializer used by gson.
     * @param json the input json from server
     * @param typeOfT the type of object to deserialize to
     * @param context context for deserialization
     * @return deserialized Room object
     * @throws JsonParseException exception if parsing the json to object is impossible
     */
    @Override
    public Room deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Turn json string to JsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        // Get the parameters of the json and parse them to object Room parameters.
        long id = jsonObject.get("id").getAsLong();
        String title = jsonObject.get("title").getAsString();
        boolean repeatingLecture = jsonObject.get("repeatingLecture").getAsBoolean();
        int tooFast = jsonObject.get("tooFast").getAsInt();
        int tooSlow = jsonObject.get("tooSlow").getAsInt();
        int normalSpeed = jsonObject.get("normalSpeed").getAsInt();

        // Try to parse the input Date format.
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
                    .parse(jsonObject.get("timeCreated").getAsString());
        } catch (ParseException e) {
            date = new Date();
        }

        return new Room(id, title, date, repeatingLecture, tooFast, tooSlow, normalSpeed);
    }
}
