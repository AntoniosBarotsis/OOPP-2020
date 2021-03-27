package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Room controller.
 */
@RestController("RoomV1")
@RequestMapping("api/v1/rooms")
public class RoomControllerV1 {
    @Autowired
    private final RoomService roomService;

    /**
     * Instantiates a new Room controller.
     *
     * @param roomService the room service
     */
    public RoomControllerV1(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Returns a list of all rooms.
     *
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping
    public List<Room> findAll() throws JsonProcessingException {
        return roomService.findAll();
    }

    /**
     * Gets the room that has the passed id.
     *
     * @param id the id
     * @return the one
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("{id}")
    public Room getOne(@PathVariable long id) throws JsonProcessingException {
        return roomService.getOne(id);
    }

    /**
     * Gets a room's public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @GetMapping("public/{roomId}")
    public String getPublicPassword(@PathVariable long roomId) {
        return roomService.getPublicPassword(roomId);
    }

    /**
     * Gets a room's private password if the request comes from an ip registered as a moderator
     * in said room.
     *
     * @param ip     the ip
     * @param roomId the room id
     * @return the private password
     */
    @GetMapping("private/{roomId}/{ip}")
    public String getPrivatePassword(@PathVariable String ip,
                                     @PathVariable long roomId) {
        return roomService.getPrivatePassword(roomId, ip);
    }

    /**
     * Gets a room's list of questions.
     *
     * @param roomId the room id
     * @return the set
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping("questions/{roomId}")
    public Set<Question> findAllQuestions(@PathVariable long  roomId) throws JsonProcessingException {
        return roomService.findAllQuestions(roomId);
    }

    /**
     * Find all polls set.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("polls/{roomId}")
    public Set<Poll> findAllPolls(@PathVariable long roomId) {
        return roomService.findAllPolls(roomId);
    }

    /**
     * Increments the too the tooFast attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/increment/{roomId}")
    public void incrementTooFast(@PathVariable long roomId) {
        roomService.incrementTooFast(roomId);
    }

    /**
     * Decrements the too the tooFast attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/decrement/{roomId}")
    public void decrementTooFast(@PathVariable long roomId) {
        roomService.decrementTooFast(roomId);
    }

    /**
     * Increment the tooSlow attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/increment/{roomId}")
    public void incrementTooSlow(@PathVariable long roomId) {
        roomService.incrementTooSlow(roomId);
    }

    /**
     * Decrement the tooSlow attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/decrement/{roomId}")
    public void decrementTooSlow(@PathVariable long roomId) {
        roomService.decrementTooSlow(roomId);
    }
}
