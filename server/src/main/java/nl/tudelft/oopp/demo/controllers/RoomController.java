package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Set;
import javax.websocket.server.PathParam;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The type Room controller.
 */
@RestController("Room")
@RequestMapping("api/v1/rooms")
@AllArgsConstructor
public class RoomController {
    private final RoomService roomService;

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
     * Gets one.
     *
     * @param id the id
     * @return the one
     */
    @GetMapping
    public Room getOne(@PathParam("id") long id) {
        return roomService.getOne(id);
    }

    /**
     * Gets public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @GetMapping("public")
    public String getPublicPassword(@PathParam("roomId") long roomId) {
        return roomService.getPublicPassword(roomId);
    }

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    @GetMapping("private")
    public String getPrivatePassword(@PathParam("roomId") long roomId) {
        return roomService.getPrivatePassword(roomId);
    }

    /**
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping(value = "questions", produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAllQuestions(@PathParam("roomId") long roomId)
        throws JsonProcessingException {
        return roomService.findAllQuestions(roomId);
    }

    /**
     * Find all polls set.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("polls")
    public Set<Poll> findAllPolls(@PathParam("roomId") long roomId) {
        return roomService.findAllPolls(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/increment")
    public void incrementTooFast(@PathParam("roomId") long roomId) {
        roomService.incrementTooFast(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/decrement")
    public void decrementTooFast(@PathParam("roomId") long roomId) {
        roomService.decrementTooFast(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/increment")
    public void incrementTooSlow(@PathParam("roomId") long roomId) {
        roomService.incrementTooSlow(roomId);
    }

    /**
     * Increment too fast.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/decrement")
    public void decrementTooSlow(@PathParam("roomId") long roomId) {
        roomService.decrementTooSlow(roomId);
    }
}
