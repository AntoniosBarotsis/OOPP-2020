package nl.tudelft.oopp.demo.repositories;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.LinkedList;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.User;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

@DataJpaTest
class PollRepositoryTest {

    @Autowired
    private PollRepository pollRepository;
    @Autowired
    private RoomRepository roomRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private RoomConfigRepository roomConfigRepository;


    private Room room;
    private User user;
    private Poll poll;
    private RoomConfig roomConfig;

    @BeforeEach
    void setUp() {
        poll = new Poll("Question", new LinkedList<>(), new LinkedList<>());

        user = new ElevatedUser("Admin", "IP 1", true);
        roomConfig = new RoomConfig();
        room = new Room("Room", false, (ElevatedUser) user, roomConfig);

        roomConfigRepository.save(roomConfig);
        userRepository.save(user);
        roomRepository.save(room);
        pollRepository.save(poll);
    }


    @Test
    void updateStatus() {
        assertEquals("OPEN", pollRepository.getStatus(poll.getId()));
        pollRepository.updateStatus(poll.getId(), Poll.PollStatus.STATISTICS);
        assertEquals("STATISTICS", pollRepository.getStatus(poll.getId()));

    }

    @Test
    void getStatus() {
        assertEquals("OPEN", pollRepository.getStatus(poll.getId()));

    }
}