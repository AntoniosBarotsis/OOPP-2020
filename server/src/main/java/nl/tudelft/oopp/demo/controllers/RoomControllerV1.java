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
     * Find all list.
     *
     * @return the list
     */
    @GetMapping
    public String findAll() throws JsonProcessingException {
        return roomService.findAll();
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @GetMapping("{id}")
    public String getOne(@PathVariable long id) throws JsonProcessingException {
        return roomService.getOne(id);
    }

    /**
     * Gets public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @GetMapping("public/{roomId}")
    public String getPublicPassword(@PathVariable long roomId) {
        return roomService.getPublicPassword(roomId);
    }

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    @GetMapping("private/{roomId}/{ip}")
    public String getPrivatePassword(@PathVariable String ip,
                                     @PathVariable long roomId) {
        return roomService.getPrivatePassword(roomId, ip);
    }

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("questions/{roomId}")
    public String findAllQuestions(@PathVariable long  roomId) throws JsonProcessingException {
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
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/increment/{roomId}")
    public void incrementTooFast(@PathVariable long roomId) {
        roomService.incrementTooFast(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/decrement/{roomId}")
    public void decrementTooFast(@PathVariable long roomId) {
        roomService.decrementTooFast(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/increment/{roomId}")
    public void incrementTooSlow(@PathVariable long roomId) {
        roomService.incrementTooSlow(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/decrement/{roomId}")
    public void decrementTooSlow(@PathVariable long roomId) {
        roomService.decrementTooSlow(roomId);
    }
}
