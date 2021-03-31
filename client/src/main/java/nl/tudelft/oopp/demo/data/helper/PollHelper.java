package nl.tudelft.oopp.demo.data.helper;

import java.util.List;
import nl.tudelft.oopp.demo.data.Poll;


/**
 * The Poll helper class.
 */

public class PollHelper {

    private String text;
    private List<String> options;
    private List<String> correctAnswer;
    private Poll.PollStatus status;

    /**
     * Constructor for pollHelper.
     *
     * @param text the poll text
     * @param options the options of the poll
     * @param correctAnswer the correct answers in the poll
     * @param status the poll status
     */
    public PollHelper(String text, List<String> options, List<String> correctAnswer
            , Poll.PollStatus status) {
        this.text = text;
        this.options = options;
        this. correctAnswer = correctAnswer;
        this.status = status;
    }

    public Poll createPoll() {
        return new Poll(text, options, correctAnswer, status);
    }


}
