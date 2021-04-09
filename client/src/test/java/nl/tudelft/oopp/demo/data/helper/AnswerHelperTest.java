package nl.tudelft.oopp.demo.data.helper;

import java.util.List;
import nl.tudelft.oopp.demo.communication.polls.AnswerPollCommunication;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class AnswerHelperTest {
    @Test
    void constructor() {
        AnswerHelper studentHelper = new AnswerHelper(1, 1, List.of("Answer 1", "Answer 2"));
        assertNotNull(studentHelper);
    }
}