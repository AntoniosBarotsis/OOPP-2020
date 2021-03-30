package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.services.PollService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
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
     * @param title the Poll's title
     * @param text the Poll's text
     * @param options the Poll's options
     * @param answers the Poll's answers
     * @return the newly created Poll
     * @throws JsonProcessingException the json processing exception
     */
    @PutMapping("create")
    public String createPoll(@PathParam("title") String title, @PathParam("text") String text,
                             @PathParam("options") List<String> options,
                             @PathParam("answers") List<String> answers)
            throws JsonProcessingException {
        return pollService.createPoll(title, text, options, answers);
    }

    /**
     * Creates a poll.
     *
     * @param poll the poll
     * @throws JsonProcessingException the json processing exception
     */
    @PutMapping("createPoll")
    public String createPoll(@RequestBody Poll poll)
            throws JsonProcessingException {
        return pollService.createPoll(poll);
    }
}
