package nl.tudelft.oopp.demo.data.deserializers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.google.gson.Gson;
import nl.tudelft.oopp.demo.data.Question;
import org.junit.jupiter.api.Test;

import java.text.SimpleDateFormat;

class QuestionInstanceCreatorTest {

    private static Gson gson = new Gson();

    @Test
    void testDeserialization() {
        String json = "{ \"answer\": \"text answer\", \"answered\": true, " +
                "\"author\": { \"id\": 2, \"questionsAsked\": [ 1, 2 ], " +
                "\"questionsUpvoted\": [ 1 ], \"type\": \"STUDENT\", " +
                "\"username\": \"name\" }, \"BeingAnswered\": true, " +
                "\"id\": 1, \"score\": 10, \"QuestionStatus\": \"OPEN\", " +
                "\"text\": \"questionText\", " +
                "\"timeCreated\": \"2021-04-06 03:42:48\", \"upvotes\": 5 }";

        Question question = gson.fromJson(json, Question.class);
        assertEquals(1L, question.getId());
        assertEquals("name", question.getAuthor().getUsername());
        assertEquals("text answer", question.getAnswer());
        assertEquals(2L, question.getAuthor().getId());
        assertEquals(Question.QuestionStatus.OPEN, question.getStatus());
        assertTrue(question.getIsBeingAnswered());
        assertEquals(5, question.getUpvotes());
        assertEquals(10, question.getScore());
        assertEquals("2021-04-06 03:42:48",
                new SimpleDateFormat("yyyy-MM-dd hh:mm:ss")
                        .format(question.getTimeCreated()));
    }
}