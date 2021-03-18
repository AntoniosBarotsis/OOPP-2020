package nl.tudelft.oopp.demo.data.deserializers;

import com.google.gson.Gson;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import nl.tudelft.oopp.demo.data.User;

public class UserInstanceCreator implements JsonDeserializer<User> {
    /**
     * Custom deserializer used by gson.
     * @param json the input json from server
     * @param typeOfT the type of object to deserialize to
     * @param context context for deserialization
     * @return deserialized User object
     * @throws JsonParseException exception if parsing the json to object is impossible
     */
    @Override
    public User deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Turn json string to JsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        // Get the parameters of the json and parse them to object Room parameters.
        long id = jsonObject.get("id").getAsLong();
        String username = jsonObject.get("username").getAsString();

        // Set the ENUM.
        String statusString = jsonObject.get("userType").getAsString();
        User.UserType type = User.UserType.STUDENT;
        if (statusString.equals("LECTURER")) {
            type = User.UserType.LECTURER;
        } else if (statusString.equals("MODERATOR")) {
            type = User.UserType.MODERATOR;
        } else {
            type = User.UserType.STUDENT;
        }

        // Get the sets of question ids.
        Gson gson = new Gson();
        Set<Long> questionsAsked = gson
                    .fromJson(jsonObject.get("questionsAsked").getAsJsonArray(),
                            new TypeToken<HashSet<Long>>(){}.getType());
        Set<Long> questionsUpvoted = gson
                    .fromJson(jsonObject.get("questionsUpvoted").getAsJsonArray(),
                            new TypeToken<HashSet<Long>>(){}.getType());

        return new User(id, username, questionsAsked, questionsUpvoted, type);
    }
}
