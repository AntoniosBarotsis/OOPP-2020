package nl.tudelft.oopp.demo.entities.helpers;

import java.util.List;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Answer;

/**
 * The Poll helper class.
 */
@Data
@NoArgsConstructor
public class AnswerHelper {

    private long pollId;
    private long userId;
    private List<String> answers;

    /**
     * Create an Answer.
     *
     * @return the Answer
     */
    public Answer createAnswer() {
        return new Answer(answers, pollId, userId);
    }

}
