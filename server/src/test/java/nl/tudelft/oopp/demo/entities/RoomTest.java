package nl.tudelft.oopp.demo.entities;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.Stream;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.repositories.PollRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@DataJpaTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
class RoomTest {
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private QuestionRepository questionRepository;

    private ElevatedUser u1;
    private ElevatedUser u2;
    private Poll p1;
    private Question q1;
    private Question q2;
    private Room r1;

    @BeforeEach
    void setUp() {
        u1 = new ElevatedUser("Admin", "ip", true);
        u2 = new ElevatedUser("Mod", "ip");
        //User u22 = new ElevatedUser("Mod2", "ip", false);
        //User u3 = new Student("Student", "ip");
        //userRepository.saveAll(List.of(u1, u2, u3, u22));
        userRepository.saveAll(List.of(u1));

        p1 = new Poll("Poll title", "Poll text", new ArrayList<>(),
            List.of("Correct answer"));
        pollRepository.saveAll(List.of(p1));

        q1 = new Question("Question text", u1);
        q2 = new Question("Question text 2", u1);
        //questionRepository.saveAll(List.of(q1, q2));
        questionRepository.saveAll(List.of(q2));

        r1 = new Room("Room Title", false, u1);

        r1.setQuestions(Stream.of(q2)
            .collect(Collectors.toSet())
        );
        r1.setPolls(Stream.of(p1)
            .collect(Collectors.toSet())
        );
        roomRepository.save(r1);
    }

    @Test
    @DirtiesContext
    void injectedComponentsAreNotNull() {
        assertThat(roomRepository).isNotNull();
        assertThat(userRepository).isNotNull();
        assertThat(pollRepository).isNotNull();
        assertThat(questionRepository).isNotNull();
    }

    @Test
    void getId() {
        assertThat(r1.getId()).isEqualTo(1L);
    }

    @Test
    void setId() {
        r1.setId(2L);
        assertThat(r1.getId()).isEqualTo(2L);
    }

    @Test
    void getTitle() {
        assertThat(r1.getTitle()).isEqualTo("Room Title");
    }

    @Test
    void setTitle() {
        r1.setTitle("Room Title 2");
        assertThat(r1.getTitle()).isEqualTo("Room Title 2");
    }

    @Test
    void getStartingDate() {
        assertThat(r1.getStartingDate()).isCloseTo(new Date(), 1000);
    }

    @Test
    void setStartingDate() {
        Date date = new Date(42L);
        r1.setStartingDate(date);
        assertThat(r1.getStartingDate()).isEqualTo(date);
    }

    @Test
    void isRepeatingLecture() {
        assertThat(r1.isRepeatingLecture()).isFalse();
    }

    @Test
    void setRepeatingLecture() {
        r1.setRepeatingLecture(true);
        assertThat(r1.isRepeatingLecture()).isTrue();
    }

    @Test
    void getAdmin() {
        assertThat(r1.getAdmin()).isEqualTo(u1.getId());
    }

    @Test
    void setAdmin() {
        r1.setAdmin(u2);
        assertThat(r1.getAdmin()).isEqualTo(u2.getId());
    }

    @Test
    void getModerators() {
        Set<ElevatedUser> set = new HashSet<>();
        set.add(u1);
        assertThat(r1.getModerators()).isEqualTo(set);
    }

    @Test
    void setModerators() {
        Set<ElevatedUser> set = new HashSet<>();
        set.add(u2);

        r1.setModerators(set);
        assertThat(r1.getModerators()).isEqualTo(set);
    }

    @Test
    void getBannedIps() {
        assertThat(r1.getBannedIps()).isEqualTo(new HashSet<>());
    }

    @Test
    void setBannedIps() throws UnknownHostException {
        Set<String> set = new HashSet<>();
        String ip = InetAddress.getLocalHost().toString();
        set.add(ip);

        r1.setBannedIps(set);
        assertThat(r1.getBannedIps()).isEqualTo(set);
    }

    @Test
    void getQuestions() {
        Set<Question> set = new HashSet<>();
        set.add(q2);
        assertThat(r1.getQuestions()).isEqualTo(set);
    }

    @Test
    void setQuestions() {
        Set<Question> set = new HashSet<>();
        set.add(q1);

        r1.setQuestions(set);
        assertThat(r1.getQuestions()).isEqualTo(set);
    }

    @Test
    void getPolls() {
        Set<Poll> set = new HashSet<>();
        set.add(p1);
        assertThat(r1.getPolls()).isEqualTo(set);
    }

    @Test
    void setPolls() {
        Set<Poll> set = new HashSet<>();
        r1.setPolls(set);

        assertThat(r1.getPolls()).isEqualTo(set);
    }

    @Test
    void getTooFast() {
        assertThat(r1.getTooFast()).isEqualTo(0);
    }

    @Test
    void setTooFast() {
        r1.setTooFast(2);
        assertThat(r1.getTooFast()).isEqualTo(2);
    }

    @Test
    void getTooSlow() {
        assertThat(r1.getTooSlow()).isEqualTo(0);
    }

    @Test
    void setTooSlow() {
        r1.setTooSlow(2);
        assertThat(r1.getTooSlow()).isEqualTo(2);
    }

    @Test
    void getElevatedPassword() {
        String uuid = UUID.randomUUID().toString();

        assertThat(r1.getElevatedPassword()).isNotNull();
        assertThat(r1.getElevatedPassword().length()).isEqualTo(uuid
            .substring(0, uuid.length() / 2).replace("-", "").length());
    }

    @Test
    void setElevatedPassword() {
        r1.setElevatedPassword("verysecurepassword");

        assertThat(r1.getElevatedPassword()).isEqualTo("verysecurepassword");
    }

    @Test
    void getNormalPassword() {
        String uuid = UUID.randomUUID().toString();

        assertThat(r1.getNormalPassword()).isNotNull();
        assertThat(r1.getNormalPassword().length()).isEqualTo(uuid
            .substring(uuid.length() / 2).replace("-", "").length());
    }

    @Test
    void setNormalPassword() {
        r1.setNormalPassword("42stillfunnyguys");

        assertThat(r1.getNormalPassword()).isEqualTo("42stillfunnyguys");
    }

    @Test
    void testEquals() {
        Room r2 = new Room("Room Title", false, u1);
        assertThat(r1).isNotEqualTo(r2);

        r2.setQuestions(Stream.of(q2)
            .collect(Collectors.toSet())
        );
        assertThat(r1).isNotEqualTo(r2);

        r2.setPolls(Stream.of(p1)
            .collect(Collectors.toSet())
        );
        assertThat(r1).isNotEqualTo(r2);

        r2.setId(1);
        assertThat(r1).isNotEqualTo(r2);

        r2.setNormalPassword(r1.getNormalPassword());
        assertThat(r1).isNotEqualTo(r2);
        r2.setElevatedPassword(r1.getElevatedPassword());
        assertThat(r1).isNotEqualTo(r2);

        r2.setStartingDate(r1.getStartingDate());
        assertThat(r1).isEqualTo(r2);

        assertThat(r1).isEqualTo(r1);
        assertThat(r1).isNotEqualTo(new Object());
    }

    @Test
    void testHashCode() {
        Room r2 = new Room("Room Title", false, u1);

        assertThat(r1.hashCode()).isEqualTo(r1.hashCode());
        assertThat(r1.hashCode()).isNotEqualTo(r2.hashCode());
    }

    @Test
    void testToString() {
        String questionToString = "";
        for (Question question : r1.getQuestions()) {
            questionToString += question.toString();
        }

        String pollToString = "";
        for (Poll poll : r1.getPolls()) {
            pollToString += poll.toString();
        }

        String str = "Room(id=1, title=Room Title, startingDate=" + r1.getStartingDate() + ", "
            + "repeatingLecture=false, admin=1, moderators=[User(id=1, username=Admin, ip=ip, "
            + "questionsAsked=[], questionsUpvoted=[], type=ADMIN)], bannedIps=[], questions=["
            + "" + questionToString + "], polls=[" + pollToString + "], tooFast=0, tooSlow=0, "
            + "elevatedPassword=" + r1.getElevatedPassword() + ", "
            + "normalPassword=" + r1.getNormalPassword() + ")";

        assertThat(str).isEqualTo(r1.toString());
    }
}