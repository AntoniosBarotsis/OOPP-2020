package nl.tudelft.oopp.demo.data.helper;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PollHelperTest {
    @Test
    void constructor() {
        List<String> options = new ArrayList<>();
        options.add("Answer 2");
        options.add("Answer 1");
        PollHelper pollHelper = new PollHelper("name", options, options.subList(0, 0));
        assertNotNull(pollHelper);
    }
}