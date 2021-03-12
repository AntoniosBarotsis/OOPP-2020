package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
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
     * @param questionId the question id
     */
    public String exportToJson(long questionId) throws JsonProcessingException {
        if (questionRepository.findById(questionId).isPresent()) {
            return questionRepository.findById(questionId).get().exportToJson();
        } else {
            return "{\"error\": \"JsonProcessingException\"}";
        }
    }

    /**
     * Exports all questions from a given room in JSON format.
     * @param roomId the room id
     */
    public String exportAllToJson(long roomId) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();

        return objMapper.writeValueAsString(roomRepository.findAllQuestions(roomId));
    }
}
