package nl.tudelft.oopp.demo.services;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;

    public void addQuestion(Question question, long roomId) {
        userRepository.save(question.getAuthor());
        questionRepository.save(question);
        questionRepository.addQuestion(roomId, question.getId());
    }

    public void deleteOneQuestion(long roomId, long questionId) {
        questionRepository.deleteOneQuestion(roomId, questionId);
    }

    public void deleteAllQuestions(long roomId) {
        questionRepository.deleteAllQuestions(roomId);
    }
}
