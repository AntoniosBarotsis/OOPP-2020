package nl.tudelft.oopp.demo.communication.polls;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.Arrays;
import nl.tudelft.oopp.demo.data.helper.AnswerHelper;
import org.junit.jupiter.api.Test;


public class AnswerPollCommunicationTest {

    @Test
    void createAnswer() {
        AnswerHelper answerHelper = new AnswerHelper(1, 1, Arrays.asList("Test"));
        assertDoesNotThrow(() -> AnswerPollCommunication.createAnswer(answerHelper));
    }

    @Test
    void getPoll() {
        assertNotNull(AnswerPollCommunication.getPoll(1));
    }

    @Test
    void getAnswerAmount() {
        assertNotNull(AnswerPollCommunication.getAnswerAmount(1, "a"));
    }

    @Test
    void getTotalAnswerAmount() {
        assertNotNull(AnswerPollCommunication.getTotalAnswerAmount(1));
    }

    @Test
    void hasAnswered() {
        assertNotNull(AnswerPollCommunication.hasAnswered(1, 1));
    }
}
