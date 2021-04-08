package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.helpers.AnswerHelper;
import nl.tudelft.oopp.demo.services.AnswerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Answer controller.
 */
@RestController("Answer")
@RequestMapping("api/v1/answers")
@AllArgsConstructor
public class AnswerController {

    @Autowired
    private final AnswerService answerService;

    /**
     * Find all list.
     *
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll() throws JsonProcessingException {
        return answerService.findAll();
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOne(@PathParam("id") long id) throws JsonProcessingException {
        return answerService.getOne(id);
    }

    /**
     * Create an Answer.
     *
     * @param answerHelper the AnswerHelper
     * @throws JsonProcessingException the json processing exception
     */
    @PutMapping("create")
    public void create(@RequestBody AnswerHelper answerHelper) throws JsonProcessingException {
        answerService.create(answerHelper);
    }

    /**
     * Check whether a student has answered a poll.
     *
     * @param pollId the poll id
     * @param userId the user id
     */
    @GetMapping("hasAnswered")
    public boolean hasAnswered(@PathParam("pollId") long pollId, @PathParam("userId") long userId) {
        return answerService.hasAnswered(pollId, userId);
    }
}
