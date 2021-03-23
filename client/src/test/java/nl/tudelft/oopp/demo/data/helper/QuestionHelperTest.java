package nl.tudelft.oopp.demo.data.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class QuestionHelperTest {

    @Test
    void constructor() {
        StudentHelper studentHelper = new StudentHelper("name", "ip");
        QuestionHelper questionHelper = new QuestionHelper("text", studentHelper);
        assertNotNull(questionHelper);
    }
}