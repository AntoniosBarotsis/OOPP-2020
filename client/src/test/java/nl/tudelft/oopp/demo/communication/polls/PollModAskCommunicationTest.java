package nl.tudelft.oopp.demo.communication.polls;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.data.Poll;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.helper.PollHelper;
import org.junit.jupiter.api.Test;

class PollModAskCommunicationTest {

    @Test
    void createPoll() {
        PollHelper testPoll = new PollHelper("poll", new ArrayList<>(), new ArrayList<>());
        assertDoesNotThrow(() -> PollModAskCommunication.createPoll(testPoll, new Room(1,
                "roomTitle", new Date(), false, 0, 0,0,
                true, new RoomConfig(300,300,300,300))));
    }

    @Test
    void setStatusClosed() {
        assertDoesNotThrow(() -> PollModAskCommunication.setStatus(1, Poll.PollStatus.CLOSED));
    }

    @Test
    void setStatusOpen() {
        assertDoesNotThrow(() -> PollModAskCommunication.setStatus(1, Poll.PollStatus.OPEN));
    }

    @Test
    void getOcurrences() {
        List<String> optionsTest = new ArrayList<>();
        optionsTest.add("TrueOption");
        optionsTest.add("FalseOption");
        List<String> answer = new ArrayList<>();
        answer.add("TrueOption");

        Poll test = new Poll(1, "Question", new Date(), optionsTest, answer, Poll.PollStatus.OPEN);
        // assertNotNull(PollModAskCommunication.getAnswerOccurences(1, "TrueOption"));
        // assertDoesNotThrow(() -> PollModAskCommunication.getAnswerOccurences(1, "TrueOption"));
    }

    @Test
    void getAnswers() {
        assertDoesNotThrow(() -> PollModAskCommunication.getNumAnswers(1));
        assertNotNull(PollModAskCommunication.getNumAnswers(1));
    }
}