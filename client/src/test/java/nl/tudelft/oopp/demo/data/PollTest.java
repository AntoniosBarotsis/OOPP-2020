package nl.tudelft.oopp.demo.data;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.jupiter.api.Test;

class PollTest {

    private List<String> correct = new ArrayList<>();
    private List<String> options = new ArrayList<>();
    private Poll poll = new Poll(858585, "what is the meaning of life",
            new Date(42), options, correct, Poll.PollStatus.OPEN);

    @Test
    void getId() {
        assertEquals(858585, poll.getId());
    }

    @Test
    void setId() {
        poll.setId(12345677);
        assertEquals(12345677, poll.getId());
    }

    @Test
    void getText() {
        assertEquals("what is the meaning of life", poll.getText());
    }

    @Test
    void setText() {
        poll.setText("what is the meaning of cpu");
        assertEquals("what is the meaning of cpu", poll.getText());
    }

    @Test
    void getTimeCreated() {
        assertEquals(new Date(42), poll.getTimeCreated());
    }

    @Test
    void setTimeCreated() {
        poll.setTimeCreated(new Date(43));
        assertEquals(new Date(43), poll.getTimeCreated());
    }

    @Test
    void getOptions() {
        options.add("42");
        options.add("43");
        assertEquals(options, poll.getOptions());
    }

    @Test
    void setOptions() {
        options.add("42");
        options.add("43");
        options.add("44");
        poll.setOptions(List.of("42", "43", "44"));
        assertEquals(options, poll.getOptions());
    }

    @Test
    void getCorrectAnswer() {
        correct.add("42");
        assertEquals(correct, poll.getCorrectAnswer());
    }

    @Test
    void setCorrectAnswer() {
        poll.setCorrectAnswer(List.of("42"));
        assertEquals(options, poll.getOptions());
    }

    @Test
    void getStatus() {
        assertEquals(Poll.PollStatus.OPEN, poll.getStatus());
    }

    @Test
    void setStatus() {
        poll.setStatus(Poll.PollStatus.CLOSED);
        assertEquals(Poll.PollStatus.CLOSED, poll.getStatus());
    }
}