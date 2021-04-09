package nl.tudelft.oopp.demo.services;

import java.util.List;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.helpers.AnswerHelper;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Answer service.
 */
@Service
@AllArgsConstructor
public class AnswerService {

    @Autowired
    private final AnswerRepository answerRepository;

    @Autowired
    private final PollRepository pollRepository;

    /**
     * Find all.
     *
     * @return json representation of all Answers
     */
    public List<Answer> findAll() {
        return answerRepository.findAll();
    }

    /**
     * Get one.
     *
     * @param id the Answer's ID
     * @return the answer
     */
    public Answer getOne(long id) {
        return answerRepository.getOne(id);
    }

    /**
     * Create a new Answer.
     *
     * @param answerHelper the AnswerHelper
     * @return the newly created Answer
     */
    public Answer create(AnswerHelper answerHelper) {
        Answer answer = answerHelper.createAnswer();
        answerRepository.save(answer);
        Poll poll = pollRepository.getOne(answerHelper.getPollId());
        poll.addAnswer(answer);
        pollRepository.save(poll);
        return answer;
    }

    /**
     * Check whether a student has answered a poll.
     *
     * @param pollId the poll id
     * @param userId the user id
     */
    public boolean hasAnswered(long pollId, long userId) {
        Poll poll = pollRepository.getOne(pollId);
        for (Answer answer : poll.getAnswers()) {
            if (answer.getUserId() == userId) {
                return true;
            }
        }
        return false;
    }
}
