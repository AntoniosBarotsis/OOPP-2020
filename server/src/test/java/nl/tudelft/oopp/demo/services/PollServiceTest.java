package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.PollHelper;
import nl.tudelft.oopp.demo.entities.serializers.PollSerializer;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

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

        poll1.addAnswer(answer1);
        poll2.addAnswer(answer2);

        polls = Set.of(poll1, poll2);

        pollRepository.saveAll(polls);

        room1 = new Room("Title", false, elevatedUser1, roomConfig);

        room1.addPoll(poll2);

        roomRepository.save(room1);
        pollService = new PollService(pollRepository, roomRepository, answerRepository);
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
        assertEquals(getIds(Set.of(poll2)), getIds(expectedSet));
    }

    @Test
    void findAllPolls() throws JsonProcessingException {
        assertEquals(getIds(Set.of(poll2)), getIds(deserializeCollection(pollService.findAllPolls(room1.getId()))));
    }

    @Test
    void createPoll() throws JsonProcessingException {
        String text = "Text3";
        List<String> options = List.of("A", "B", "Correct answer", "D", "E");
        List<String> correctAnswers = List.of("Correct answer");
        PollHelper pollHelper = new PollHelper(text, options, correctAnswers);
        pollService.createPoll(room1.getId(), pollHelper);
        boolean contains = false;
        List<Poll> polls = pollRepository.findAll();
        System.out.println(polls);
        for (Poll poll : polls) {
            if (poll.getText().equals(text) && correctAnswers.equals(poll.getCorrectAnswer())
                    && options.equals(poll.getOptions())) {
                contains = true;
                break;
            }
        }
        assertTrue(contains);
    }

    @Test
    void setStatus() {
        pollService.setStatus(poll1.getId(), "CLOSED");
        assertEquals("CLOSED", pollRepository.getStatus(poll1.getId()));
    }

    @Test
    void getAnswerOccurences() {
        int answerOccurences = pollService.getAnswerOccurences(poll1.getId(), "A");
        assertEquals(1, answerOccurences);
    }

    @Test
    void getNumAnswers() {
        int numAnswers = pollService.getNumAnswers(poll1.getId());
        assertEquals(1, numAnswers);
    }

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

        return objMapper.readValue(string, new TypeReference<Set<Poll>>() {
        });
    }

    Set<Long> getIds(Set<Poll> polls) {
        return polls.stream().map(Poll::getId).collect(Collectors.toSet());
    }
}
