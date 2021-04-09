package nl.tudelft.oopp.demo.services;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Answer;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.AnswerHelper;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.repositories.AnswerRepository;
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomConfigRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class AnswerServiceTest {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private AnswerRepository answerRepository;
    @Autowired
    private QuestionRepository questionRepository;
    @Autowired
    private RoomConfigRepository roomConfigRepository;
    @Autowired
    private LogEntryRepository logEntryRepository;

    private AnswerService answerService;

    private Set<Answer> answers;
    private Answer answer1;
    private Answer answer2;

    private ElevatedUser elevatedUser1;
    private Student student1;
    private Student student2;
    private Student student3;

    private Room room1;
    private Poll poll1;
    private Poll poll2;

    @BeforeEach
    void setup() {
        elevatedUser1 = new ElevatedUser("Username1", "elevated-ip", true);
        student1 = new Student("Username1", "normal-ip");
        student2 = new Student("Username2", "normal-ip");
        student3 = new Student("Username3", "normal-ip");

        userRepository.saveAll(List.of(elevatedUser1, student1, student2, student3));

        RoomConfig roomConfig1 = new RoomConfig();
        RoomConfig roomConfig2 = new RoomConfig();
        roomConfigRepository.saveAll(List.of(roomConfig1, roomConfig2));

        room1 = new Room("Title1", false, elevatedUser1, roomConfig1);

        poll1 = new Poll("Text1", List.of("A", "B", "Correct answer"),
                List.of("Correct answer"));
        List<String> options = new ArrayList<>();
        options.add("A");
        List<String> correct = new ArrayList<>();
        correct.add("A");
        poll2 = new Poll("Text2", options, correct);

        Set<Poll> polls = new HashSet<>();
        polls.add(poll1);
        room1.setPolls(polls);

        roomRepository.save(room1);

        answer1 = new Answer(List.of("A"), poll1.getId(), student1.getId());
        answer2 = new Answer(List.of("B"), poll1.getId(), student2.getId());
        poll1.addAnswer(answer1);
        poll1.addAnswer(answer2);

        pollRepository.saveAll(Set.of(poll1, poll2));
        answers = Set.of(answer1, answer2);

        answerRepository.saveAll(answers);

        answerService = new AnswerService(answerRepository, pollRepository);
    }

    @Test
    void findAll() {
        assertEquals(answers, Set.copyOf(answerService.findAll()));
    }

    @Test
    void getOne() {
        assertEquals(answer1, answerService.getOne(answer1.getId()));
    }

    @Test
    void create() {
        answerService.create(new AnswerHelper(poll2.getId(), student3.getId(),
                List.of("Correct answer")));
        assertEquals(3, answerRepository.findAll().size());
    }

    @Test
    void hasAnswered() {
        assertTrue(answerService.hasAnswered(poll1.getId(), student2.getId()));
    }

}