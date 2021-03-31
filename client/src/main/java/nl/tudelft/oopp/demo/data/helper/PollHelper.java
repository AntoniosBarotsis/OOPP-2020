package nl.tudelft.oopp.demo.data.helper;


import nl.tudelft.oopp.demo.data.Poll;

import java.util.List;

/**
 * The Poll helper class.
 */

public class PollHelper {

    private String text;
    private List<String> options;
    private List<String> correctAnswer;
    private Poll.PollStatus status;

    public PollHelper(String text, List<String> options, List<String> correctAnswer, Poll.PollStatus status) {
        this.text = text;
        this.options =options;
        this. correctAnswer = correctAnswer;
        this.status = status;
    }

    public Poll createPoll() {
        return new Poll(text, options, correctAnswer, status);
    }


}
