package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Question controller.
 */
@RestController("Question")
@RequestMapping("api/v1/questions")
@AllArgsConstructor
public class QuestionController {
    private final QuestionService questionService;

    /**
     * Add question.
     *
     * @param question the question
     * @param roomId   the room id
     */
    @PostMapping("add/{roomId}")
    public void addQuestion(@RequestBody QuestionHelper question,
                                     @PathVariable long roomId) {

        questionService.addQuestion(question.createQuestion(), roomId);
    }

    /**
     * Delete one question.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    @DeleteMapping("deleteOne")
    public void deleteOneQuestion(@PathParam("roomId") long roomId,
                                  @PathParam("questionId") long questionId) {
        questionService.deleteOneQuestion(roomId, questionId);
    }

    /**
     * Delete all questions.
     *
     * @param roomId the room id
     */
    @DeleteMapping("deleteAll")
    public void deleteAllQuestions(@PathParam("roomId") long roomId) {
        questionService.deleteAllQuestions(roomId);
    }

    /**
     * Export question to json string.
     *
     * @param questionId the question id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("exportToJson")
    public String exportToJson(@PathParam("questionId") long questionId)
        throws JsonProcessingException {
        return questionService.exportToJson(questionId);
    }

    /**
     * Export all questions from a room to json string.
     *
     * @param roomId the room id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("exportAllToJson")
    public String exportAllToJson(@PathParam("roomId") long roomId)
        throws JsonProcessingException {
        return questionService.exportAllToJson(roomId);
    }
}
