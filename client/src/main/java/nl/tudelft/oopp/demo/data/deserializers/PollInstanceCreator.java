package nl.tudelft.oopp.demo.data.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import nl.tudelft.oopp.demo.data.Poll;

public class PollInstanceCreator implements JsonDeserializer<Poll> {

    /**
     * Custom deserializer used by gson.
     * @param json the input json from server
     * @param typeOfT the type of object to deserialize to
     * @param context context for deserialization
     * @return deserialized Poll object
     * @throws JsonParseException exception if parsing the json to object is impossible
     */
    @Override
    public Poll deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Turn json string to JsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        // Get the parameters of the json and parse them to object Poll parameters.
        long id = jsonObject.get("id").getAsLong();
        String text = jsonObject.get("text").getAsString();

        // Try to parse the input Date format.
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-mm-dd hh:mm:ss")
                    .parse(jsonObject.get("timeCreated").getAsString());
        } catch (ParseException e) {
            date = new Date();
        }

        // Set the ENUM.
        String statusString = jsonObject.get("status").getAsString();
        Poll.PollStatus status = Poll.PollStatus.CLOSED;
        if (statusString.equals("OPEN")) {
            status = Poll.PollStatus.OPEN;
        } else if (statusString.equals("STATISTICS")) {
            status = Poll.PollStatus.STATISTICS;
        }

        // Get the list of options.
        Gson gson = new Gson();
        List<String> options = gson
                .fromJson(jsonObject.get("options").getAsJsonArray(),
                        new TypeToken<ArrayList<String>>(){}.getType());

        // Get the list of correct answers.
        List<String> answers = gson
                .fromJson(jsonObject.get("correctAnswer").getAsJsonArray(),
                        new TypeToken<ArrayList<String>>(){}.getType());

        return new Poll(id, text, date,
                options, answers, status);
    }
}
