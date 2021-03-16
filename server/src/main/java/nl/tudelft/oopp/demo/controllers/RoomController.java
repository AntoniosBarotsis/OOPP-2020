package nl.tudelft.oopp.demo.controllers;

import java.util.Date;
import java.util.List;
import java.util.Set;
import javax.websocket.server.PathParam;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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
     * Create a new room.
     *
     * @param userId the id of the admin of the room
     * @param title the title of the room
     * @return the newly created room
     */
    @GetMapping("create/{userId}/{title}")
    public Room createRoom(@PathVariable long userId, @PathVariable String title) {
        return roomService.createRoom(userId, title);
    }

    /**
     * Schedule a new room.
     *
     * @param userId the id of the admin of the room
     * @param title the title of the room
     * @param date the starting date/time for the room
     * @return the newly created room
     */
    @PostMapping("schedule/{userId}/{title}")
    public Room scheduleRoom(@PathVariable long userId, @PathVariable String title,
                             @RequestBody Date date) {
        return roomService.scheduleRoom(userId, title, date);
    }

    /**
     * Create a new user and have him join the room.
     *
     * @param password the room's password
     * @param username the user's username
     * @param ip the user's ip
     * @return the room which the user joined
     */
    @GetMapping("join")
    public Room join(@PathParam("password") String password, @PathParam("username") String username,
                     @PathParam("ip") String ip) {
        return roomService.join(password, username, ip);
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
