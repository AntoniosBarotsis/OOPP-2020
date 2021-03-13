package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Collection;
import java.util.Comparator;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.serializers.QuestionSerializer;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type Question service.
 */
@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;

    /**
     * Add question.
     *
     * @param question the question
     * @param roomId   the room id
     */
    public void addQuestion(Question question, long roomId) {
        userRepository.save(question.getAuthor());
        questionRepository.save(question);
        questionRepository.addQuestion(roomId, question.getId());
    }

    /**
     * Delete one question.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    public void deleteOneQuestion(long roomId, long questionId) {
        questionRepository.deleteOneQuestion(roomId, questionId);
    }

    /**
     * Delete all questions.
     *
     * @param roomId the room id
     */
    public void deleteAllQuestions(long roomId) {
        questionRepository.deleteAllQuestions(roomId);
    }

    /**
     * Exports a single question in JSON format.
     *
     * @param questionId the question id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String export(long questionId) throws JsonProcessingException {
        if (questionRepository.findById(questionId).isPresent()) {
            return map(List.of(questionRepository.findById(questionId).get()));
        } else {
            return "{\"error\": \"JsonProcessingException\"}";
        }
    }

    /**
     * Exports all questions from a given room in JSON format.
     *
     * @param roomId the room id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String exportAll(long roomId) throws JsonProcessingException {
        return map(roomRepository.findAllQuestions(roomId));
    }

    /**
     * Exports a given amount of questions in JSON format sorted by score.
     *
     * @param roomId - the room id
     * @param amount - the amount of questions
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String exportTop(long roomId, int amount) throws JsonProcessingException {
        if (amount < 1) {
            return "{\"error: \"Invalid amount supplied\"}";
        }

        List<Question> questions = roomRepository
            .findAllQuestions(roomId)
            .stream()
            .sorted(Comparator.comparingInt(Question::getScore).reversed())
            .limit(amount)
            .collect(Collectors.toList());

        return map(questions);
    }

    /**
     * Export answered questions string.
     *
     * @param roomId the room id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String exportAnswered(long roomId) throws JsonProcessingException {
        List<Question> questions = roomRepository
            .findAllQuestions(roomId)
            .stream()
            .filter(Question::isAnswered)
            .collect(Collectors.toList());

        return map(questions);
    }

    /**
     * Maps a collection of questions using a custom mapper.
     *
     * @param questions the questions
     * @return string
     * @throws JsonProcessingException the json processing exception
     */
    public String map(Collection<Question> questions) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Question.class, new QuestionSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(questions);
    }
}
