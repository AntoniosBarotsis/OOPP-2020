package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Collection;
import java.util.List;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.serializers.AnswerSerializer;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
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

    /**
     * Find all.
     *
     * @return json representation of all Answers
     * @throws JsonProcessingException the json processing exception
     */
    public String findAll() throws JsonProcessingException {
        return answerRepository.findAll().toString();
    }

    /**
     * Get one.
     *
     * @param id the Answer's ID
     * @return the answer
     * @throws JsonProcessingException the json processing exception
     */
    public String getOne(long id) throws JsonProcessingException {
        return mapAnswer(answerRepository.getOne(id));
    }

    /**
     * Map Answers.
     *
     * @param answers the answers
     * @return json representaion of the answers
     * @throws JsonProcessingException the json processing exception
     */
    public String mapAnswers(Collection<Answer> answers) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Answer.class, new AnswerSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(answers);
    }

    /**
     * Map answer.
     *
     * @param answer the answer
     * @return json representation of the Answer
     * @throws JsonProcessingException the json processing exception
     */
    public String mapAnswer(Answer answer) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Answer.class, new AnswerSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(answer);
    }

    /**
     * Create a new Answer.
     *
     * @param answers the given answers
     * @param pollId the poll's ID
     * @param userId the user's ID
     * @return the newly created Answer
     * @throws JsonProcessingException the json processing exception
     */
    public String create(List<String> answers, long pollId, long userId)
            throws JsonProcessingException {
        Answer answer = new Answer(answers, pollId, userId);
        answerRepository.save(answer);
        return mapAnswer(answer);
    }
}
