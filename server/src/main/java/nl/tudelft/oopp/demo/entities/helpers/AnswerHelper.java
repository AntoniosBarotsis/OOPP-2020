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
     * Constructor for AnswerHelper.
     *
     * @param pollId poll id
     * @param userId user id
     * @param answers answers
     */
    public AnswerHelper(long pollId, long userId, List<String> answers) {
        this.pollId = pollId;
        this.userId = userId;
        this.answers = answers;
    }

    /**
     * Create an Answer.
     *
     * @return the Answer
     */
    public Answer createAnswer() {
        return new Answer(answers, pollId, userId);
    }

}
