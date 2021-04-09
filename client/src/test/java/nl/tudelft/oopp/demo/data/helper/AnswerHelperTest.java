package nl.tudelft.oopp.demo.data.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.List;
import org.junit.jupiter.api.Test;

class AnswerHelperTest {
    @Test
    void constructor() {
        AnswerHelper studentHelper = new AnswerHelper(1, 1, List.of("Answer 1", "Answer 2"));
        assertNotNull(studentHelper);
    }
}