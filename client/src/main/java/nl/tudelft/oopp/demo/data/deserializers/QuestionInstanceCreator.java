package nl.tudelft.oopp.demo.data.deserializers;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.JsonNull;

import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.QuestionAuthor;



public class QuestionInstanceCreator implements JsonDeserializer<Question> {

    /**
     * Custom deserializer used by gson.
     * @param json the input json from server
     * @param typeOfT the type of object to deserialize to
     * @param context context for deserialization
     * @return deserialized Question object
     * @throws JsonParseException exception if parsing the json to object is impossible
     */
    @Override
    public Question deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context)
            throws JsonParseException {
        // Turn json string to JsonObject
        JsonObject jsonObject = json.getAsJsonObject();

        // Get the parameters of the json and parse them to object Question parameters.
        long id = jsonObject.get("id").getAsLong();
        String text = jsonObject.get("text").getAsString();

        JsonObject authorObject = jsonObject.get("author").getAsJsonObject();
        long authorId = authorObject.get("id").getAsLong();
        String authorUsername = authorObject.get("username").getAsString();

        int upvotes = jsonObject.get("upvotes").getAsInt();
        int score = jsonObject.get("score").getAsInt();
        String statusString = jsonObject.get("QuestionStatus").getAsString();
        boolean beingAnswered = jsonObject.get("BeingAnswered").getAsBoolean();

        // Check if answer is null.
        JsonElement nullableText = jsonObject.get("answer");
        String answer = (nullableText instanceof JsonNull) ? "" : nullableText.getAsString();

        // Set the ENUM.
        Question.QuestionStatus status = Question.QuestionStatus.OPEN;
        if (statusString.equals("OPEN")) {
            status = Question.QuestionStatus.OPEN;
        } else if (statusString.equals("ANSWERED")) {
            status = Question.QuestionStatus.ANSWERED;
        } else {
            status = Question.QuestionStatus.SPAM;
        }

        // Try to parse the input Date format.
        Date date = null;
        try {
            date = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                    .parse(jsonObject.get("timeCreated").getAsString());
        } catch (ParseException e) {
            date = new Date();
        }

        QuestionAuthor questionAuthor = new QuestionAuthor(authorId, authorUsername);
        return new Question(id, text, questionAuthor, upvotes, score,
                date, status, answer, beingAnswered);
    }
}
