package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.helpers.PollHelper;
import nl.tudelft.oopp.demo.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Poll controller.
 */
@RestController("Poll")
@RequestMapping("api/v1/polls")
@AllArgsConstructor
public class PollController {

    @Autowired
    private final PollService pollService;

    /**
     * Find all list.
     *
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll() throws JsonProcessingException {
        return pollService.findAll();
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
        return pollService.getOne(id);
    }

    /**
     * Get all open Polls.
     *
     * @param roomId the room ID
     * @return json representation of all polls
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(value = "open", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOpenPolls(@PathParam("roomId") long roomId) throws JsonProcessingException {
        return pollService.getOpenPolls(roomId);
    }

    /**
     * Get all polls.
     *
     * @param roomId the room ID
     * @return json representation of all polls
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(value = "list", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllPolls(@PathParam("roomId") long roomId) throws JsonProcessingException {
        return pollService.findAllPolls(roomId);
    }

    /**
     * Create a poll.
     *
     * @param pollHelper the Pollhelper
     * @return the newly created Poll
     * @throws JsonProcessingException the json processing exception
     */
    @PostMapping("create")
    public String createPoll(@PathParam("roomId") long roomId, @RequestBody PollHelper pollHelper)
            throws JsonProcessingException {
        return pollService.createPoll(roomId, pollHelper);
    }

    /**
     * Set Poll status.
     *
     * @param pollId the Poll's ID
     * @param status the poll status
     */
    @PutMapping("status")
    public void setStatus(@PathParam("pollId") long pollId, @PathParam("status") String status) {
        pollService.setStatus(pollId, status);
    }

    /**
     * Get the number of occurrence of an Answer.
     *
     * @param pollId the Poll's ID
     * @param answer the Answer
     * @return the number of occurrence of an Answer
     */
    @GetMapping("answerOccurrences")
    public int getAnswerOccurrences(@PathParam("pollId") long pollId,
                                    @PathParam("answer") String answer) {
        return pollService.getAnswerOccurences(pollId, answer);
    }

    /**
     * Get the number of students who have answered.
     *
     * @param pollId the Poll's ID
     * @return the number of students who have answered
     */
    @GetMapping("numAnswers")
    public int getNumAnswers(@PathParam("pollId") long pollId) {
        return pollService.getNumAnswers(pollId);
    }

}
