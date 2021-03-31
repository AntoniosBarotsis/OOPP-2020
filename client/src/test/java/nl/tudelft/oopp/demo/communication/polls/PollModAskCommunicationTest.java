package nl.tudelft.oopp.demo.communication.polls;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Date;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.helper.PollHelper;
import org.junit.jupiter.api.Test;

class PollModAskCommunicationTest {

    @Test
    void createPoll() {
        PollHelper testPoll = new PollHelper("poll", new ArrayList<>(), new ArrayList<>());
        assertDoesNotThrow(() -> PollModAskCommunication.createPoll(testPoll, 1));
    }
}