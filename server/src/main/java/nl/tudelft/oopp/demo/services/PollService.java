package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.net.URLDecoder;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.helpers.PollHelper;
import nl.tudelft.oopp.demo.entities.serializers.PollSerializer;
import nl.tudelft.oopp.demo.exceptions.InvalidPollStatusException;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
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

    @Autowired
    private final AnswerRepository answerRepository;

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
     * @param pollHelper the pollhelper
     * @return the newly created Poll
     * @throws JsonProcessingException the json processing exception
     */
    public String createPoll(long roomId, PollHelper pollHelper)
            throws JsonProcessingException {
        Poll poll = new Poll(pollHelper.getText(), pollHelper.getOptions(),
                pollHelper.getCorrectAnswer());
        pollRepository.save(poll);
        Room room = roomRepository.getOne(roomId);
        room.addPoll(poll);
        roomRepository.save(room);
        return mapPoll(poll);
    }

    /**
     * Set poll status.
     *
     * @param pollId the Poll's ID
     * @param status the poll status
     */
    public void setStatus(long pollId, String status) {
        Poll.PollStatus pollStatus;
        if (status.equalsIgnoreCase("open")) {
            pollStatus = Poll.PollStatus.OPEN;
        } else if (status.equalsIgnoreCase("closed")) {
            pollStatus = Poll.PollStatus.CLOSED;
        } else if (status.equalsIgnoreCase("statistics")) {
            pollStatus = Poll.PollStatus.STATISTICS;
        } else {
            throw new InvalidPollStatusException(status + " is not a valid PollStatus");
        }
        pollRepository.updateStatus(pollId, pollStatus);
    }

    /**
     * Get the number of occurence of an Answer.
     *
     * @param pollId the Poll's ID
     * @param answer the Answer
     * @return the number of occurence of an Answer
     */
    public int getAnswerOccurences(long pollId, String answer) {
        Poll poll = pollRepository.getOne(pollId);
        answer = URLDecoder.decode(answer, StandardCharsets.UTF_8);
        List<Answer> answers = poll.getAnswers();
        int count = 0;
        for (Answer a : answers) {
            if (a.getAnswers().contains(answer)) {
                count++;
            }
        }
        return count;
    }

    /**
     * Get the number of students who have answered.
     *
     * @param pollId the Poll's ID
     * @return the number of students who have answered
     */
    public int getNumAnswers(long pollId) {
        return answerRepository.getNumAnswers(pollId);
    }

}
