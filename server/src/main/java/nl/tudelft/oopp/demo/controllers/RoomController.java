package nl.tudelft.oopp.demo.controllers;

import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.Question;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController("Room")
@RequestMapping("api/v1/rooms")
public class RoomController {
    @Autowired
    private final RoomService roomService;

    public RoomController(RoomService roomService) {
        this.roomService = roomService;
    }

    @GetMapping
    public List<Room> findAll() {
        return roomService.findAll();
    }

    @GetMapping("{id}")
    public Room getOne(@PathVariable long id) {
        return roomService.getOne(id);
    }

    @GetMapping("public/{roomId}")
    public String getPublicPassword(@PathVariable long roomId) {
        return roomService.getPublicPassword(roomId);
    }

    @GetMapping("private/{roomId}")
    public String getPrivatePassword(@PathVariable long roomId) {
        return roomService.getPrivatePassword(roomId);
    }

    @GetMapping("questions/{roomId}")
    public Set<Question> findAllQuestions(@PathVariable long  roomId) {
        return roomService.findAllQuestions(roomId);
    }
}
