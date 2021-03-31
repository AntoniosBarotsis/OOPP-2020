package nl.tudelft.oopp.demo.communication.polls;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import java.util.ArrayList;
import java.util.Date;
import nl.tudelft.oopp.demo.data.Poll;
import org.junit.jupiter.api.Test;

class PollModAskCommunicationTest {

    @Test
    void createPoll() {
        Poll testPoll = new Poll(1, "poll", new Date(),
                new ArrayList<>(), new ArrayList<>(), Poll.PollStatus.CLOSED);
        assertDoesNotThrow(() -> PollModAskCommunication.createPoll(testPoll));
    }
}