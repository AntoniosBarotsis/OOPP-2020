package nl.tudelft.oopp.demo.controllers;

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
@RestController("Room")
@RequestMapping("api/v1/rooms")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    /**
     * Instantiates a new Room controller.
     *
     * @param roomService the room service
     */
    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    /**
     * Find all list.
     *
     * @return the list
     */
    @GetMapping
    public List<Room> findAll() {
        return roomService.findAll();
    }

    /**
     * Gets the room with a specific id.
     *
     * @param id the room id
     * @return the room
     */
    @GetMapping("{id}")
    public Room getOne(@PathVariable long id) {
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
    @GetMapping("private/{roomId}")
    public String getPrivatePassword(@PathVariable long roomId) {
        return roomService.getPrivatePassword(roomId);
    }

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("questions/{roomId}")
    public Set<Question> findAllQuestions(@PathVariable long  roomId) {
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
