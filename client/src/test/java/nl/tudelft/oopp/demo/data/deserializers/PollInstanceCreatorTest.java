package nl.tudelft.oopp.demo.data.deserializers;

import com.google.gson.Gson;
import java.text.SimpleDateFormat;
import nl.tudelft.oopp.demo.data.Poll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PollInstanceCreatorTest {

    private static Gson gson = new Gson();

    @Test
    void testDeserialization() {
        String json = "{ \"id\": 1, \"text\": \"Text\", "
                + "\"status\": \"CLOSED\", \"timeCreated\": \"2021-04-06 03:42:48\", "
                + "\"correctAnswer\": [\"Answer 1\"], "
                + "\"options\": [\"Answer 1\", \"Answer 2\"] "
                + " }";

        Poll newPoll = gson.fromJson(json, Poll.class);

        Object[] expectedOptions = new String[2];
        expectedOptions[0] = "Answer 1";
        expectedOptions[1] = "Answer 2";

        Object[] expectedCorrect = new String[1];
        expectedCorrect[0] = "Answer 1";

        assertEquals(1, newPoll.getId());
        assertEquals("CLOSED", newPoll.getStatus().toString());
        assertEquals("Text", newPoll.getText());

        int counter2 = 0;
        for(String str: newPoll.getOptions()){
            assertEquals(str, expectedOptions[counter2]);
            counter2++;
        }

        int counter1 = 0;
        for(String str: newPoll.getCorrectAnswer()){
            assertEquals(str, expectedCorrect[counter1]);
            counter1++;
        }

        assertEquals("2021-04-06 03:42:48",
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .format(newPoll.getTimeCreated()));
    }
}