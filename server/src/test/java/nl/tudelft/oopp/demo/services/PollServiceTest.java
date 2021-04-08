package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.node.ArrayNode;
import com.fasterxml.jackson.databind.node.ObjectNode;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.PollHelper;
import nl.tudelft.oopp.demo.entities.serializers.PollSerializer;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.*;
import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;

@DataJpaTest
public class PollServiceTest {

    @Autowired
    private PollRepository pollRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AnswerRepository answerRepository;

    @Autowired
    private RoomConfigRepository roomConfigRepository;

    private PollService pollService;

    private ElevatedUser elevatedUser1;
    private Student student1;

    private Answer answer1;
    private Answer answer2;

    private Room room1;

    private Poll poll1;
    private Poll poll2;

    private Set<Poll> polls;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username", "ip", true);
        student1 = new Student("Username", "ip");

        userRepository.saveAll(List.of(elevatedUser1, student1));

        RoomConfig roomConfig = new RoomConfig();
        roomConfigRepository.save(roomConfig);


        poll1 = new Poll("Text1", List.of("A", "B", "Correct answer"),
                List.of("Correct answer"));

        poll2 = new Poll("Text2", List.of("A", "B", "Correct answer", "D"),
                List.of("Correct answer"));

        answer1 = new Answer(List.of("A"), poll1.getId(), student1.getId());
        answer2 = new Answer(List.of("B"), poll2.getId(), student1.getId());

        answerRepository.saveAll(List.of(answer1, answer2));

        poll1.setAnswers(null);
        poll2.setAnswers(null);

        polls = Set.of(poll1, poll2);

        pollRepository.saveAll(polls);

        room1 = new Room("Title", false, elevatedUser1, roomConfig);

        room1.setPolls(Set.of(poll2));

        roomRepository.save(room1);
        pollService = new PollService(pollRepository, roomRepository, answerRepository);

        System.out.println(pollRepository.findAll());
        try {
            System.out.println(pollService.findAll());
        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

    @Test
    void findAll() throws JsonProcessingException {
        assertEquals(getIds(polls), getIds(deserializeCollection(pollService.findAll())));
    }

    @Test
    void getOne() throws JsonProcessingException {
        assertEquals(poll1.getId(), deserializeOne(pollService.getOne(poll1.getId())).getId());
    }

    @Test
    void mapPolls() throws JsonProcessingException {
        assertEquals(getIds(polls), getIds(deserializeCollection(pollService.mapPolls(polls))));
    }

    @Test
    void mapPoll() throws JsonProcessingException {
        assertEquals(poll1.getId(), deserializeOne(pollService.mapPoll(poll1)).getId());
    }

    @Test
    void getOpenPolls() throws JsonProcessingException {
        String expected = pollService.getOpenPolls(room1.getId());
        Set<Poll> expectedSet = deserializeCollection(expected);
        assertEquals(getIds(polls), getIds(expectedSet));
    }

    @Test
    void findAllPolls() throws JsonProcessingException {
        assertEquals(getIds(Set.of(poll2)), getIds(deserializeCollection(pollService.findAllPolls(room1.getId()))));
    }

//    @Test
//    void createPoll() {
//        PollHelper
//        pollService.createPoll(room1.getId(), );
//    }

    Poll deserializeOne(String string) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objMapper.setDateFormat(dateFormat);

        return objMapper.readValue(string, Poll.class);
    }

    Set<Poll> deserializeCollection(String string) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objMapper.setDateFormat(dateFormat);

        return objMapper.readValue(string, new TypeReference<Set<Poll>>() {});
    }

    ArrayNode getObjectNode(String string) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objMapper.setDateFormat(dateFormat);
        return (ArrayNode) objMapper.readTree(string);
    }

    String serialize(Collection<Poll> polls) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objMapper.setDateFormat(dateFormat);

        return objMapper.writeValueAsString(polls);
    }

    String serialize(Poll poll) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(Poll.class, new PollSerializer());
        objMapper.registerModule(module);

        DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
        objMapper.setDateFormat(dateFormat);

        return objMapper.writeValueAsString(poll);
    }

    Set<Long> getIds(Set<Poll> polls) {
        return polls.stream().map(Poll::getId).collect(Collectors.toSet());
    }
}
