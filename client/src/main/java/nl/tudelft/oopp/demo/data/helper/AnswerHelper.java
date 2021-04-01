package nl.tudelft.oopp.demo.data.helper;

import java.util.List;

/**
 * The Answer helper class.
 */
public class AnswerHelper {

    private long pollId;
    private long userId;
    private List<String> answers;

    /**
     * Constructor for AnswerHelper.
     *
     * @param pollId  the poll id
     * @param userId  the user id
     * @param answers the answers
     */
    public AnswerHelper(long pollId, long userId, List<String> answers) {
        this.pollId = pollId;
        this.userId = userId;
        this.answers = answers;
    }
}
