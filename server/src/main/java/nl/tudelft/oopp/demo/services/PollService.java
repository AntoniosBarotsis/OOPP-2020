package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.serializers.PollSerializer;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * The Poll service.
 */
@Service
@AllArgsConstructor
public class PollService {

    @Autowired
    private final PollRepository pollRepository;

    @Autowired
    private final RoomRepository roomRepository;

    public String findAll() throws JsonProcessingException {
        return mapPolls(pollRepository.findAll());
    }

    /**
     * Get one Poll.
     *
     * @param id the Poll's ID
     * @return the Poll
     * @throws JsonProcessingException the json processing exception
     */
    public String getOne(long id) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(pollRepository.getOne(id));
    }

    /**
     * Maps polls.
     *
     * @param polls the polls
     * @return json representation of the polls
     * @throws JsonProcessingException the json processing exception
     */
    public String mapPolls(Collection<Poll> polls) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(polls);
    }

    /**
     * Map one Poll.
     *
     * @param poll the poll
     * @return json representation of the poll
     * @throws JsonProcessingException the json processing exception
     */
    public String mapPoll(Poll poll) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(poll);
    }

    /**
     * Get all open Polls.
     *
     * @param roomId the room ID
     * @return json representation of all polls
     * @throws JsonProcessingException the json processing exception
     */
    public String getOpenPolls(long roomId) throws JsonProcessingException {
        return mapPolls(roomRepository.findAllPolls(roomId).stream()
                .filter(poll -> poll.getStatus() == Poll.PollStatus.OPEN)
                .collect(Collectors.toList()));
    }

    /**
     * Get all polls.
     *
     * @param roomId the room ID
     * @return json representation of all polls
     * @throws JsonProcessingException the json processing exception
     */
    public String findAllPolls(long roomId) throws JsonProcessingException {
        return mapPolls(roomRepository.findAllPolls(roomId));
    }

    /**
     * Close a poll.
     *
     * @param pollId the Poll's ID
     */
    public void closePoll(long pollId) {
        pollRepository.updateStatus(pollId, Poll.PollStatus.CLOSED);
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
    public String createPoll(String title, String text, String[] options, String[] answers)
            throws JsonProcessingException {
        Poll poll = new Poll(title, text, Arrays.asList(options), Arrays.asList(answers));
        pollRepository.save(poll);
        return mapPoll(poll);
    }
}
