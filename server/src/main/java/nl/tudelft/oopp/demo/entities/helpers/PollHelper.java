package nl.tudelft.oopp.demo.entities.helpers;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;

/**
 * The Poll helper class.
 */
@Data
@NoArgsConstructor
public class PollHelper {

    private String text;
    private List<String> options;
    private List<String> correctAnswer;

    /**
     * Creating a poll.
     *
     * @return the Poll
     */
    public Poll createPoll() {
        return new Poll(text, options, correctAnswer);
    }

}
