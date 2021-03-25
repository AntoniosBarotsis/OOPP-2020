package nl.tudelft.oopp.demo.data.helper;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class StudentHelperTest {

    @Test
    void constructor() {
        StudentHelper studentHelper = new StudentHelper("name", "ip");
        assertNotNull(studentHelper);
    }
}