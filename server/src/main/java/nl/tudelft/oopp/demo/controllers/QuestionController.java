package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.services.QuestionService;
import nl.tudelft.oopp.demo.services.UserService;
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
    private final UserService userService;

    /**
     * Add question.
     *
     * @param roomId   the room id
     */
    @PostMapping("add/")
    public void addQuestion(@RequestBody QuestionHelper questionHelper,
                            @PathParam("roomId") long roomId,
                            @PathParam("authorId") long authorId) {

        Question question = questionHelper.createQuestion();
        question.getAuthor().setId(authorId);

        if (userService.isInvalidAuthorId(question.getAuthor())) {
            throw new RuntimeException("The supplied author id is invalid");
        }

        questionService.addQuestion(question, roomId);
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
    @GetMapping("export")
    public String export(@PathParam("questionId") long questionId)
        throws JsonProcessingException {
        return questionService.export(questionId);
    }

    /**
     * Export all questions from a room to json string.
     *
     * @param roomId the room id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("exportAll")
    public String exportAll(@PathParam("roomId") long roomId)
        throws JsonProcessingException {
        return questionService.exportAll(roomId);
    }


    /**
     * Export top {amount} of questions to json string.
     *
     * @param roomId the room id
     * @param amount the amount
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("exportTop")
    public String exportTop(@PathParam("roomId") long roomId,
                            @PathParam("amount") int amount)
        throws JsonProcessingException {
        return questionService.exportTop(roomId, amount);
    }

    /**
     * Export answered questions only string.
     *
     * @param roomId the room id
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("exportAnswered")
    public String exportAnswered(@PathParam("roomId") long roomId)
        throws JsonProcessingException {
        return questionService.exportAnswered(roomId);
    }
}
