package nl.tudelft.oopp.demo.controllers;

import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.web.bind.annotation.DeleteMapping;
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
}
