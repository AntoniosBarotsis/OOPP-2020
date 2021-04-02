package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import java.util.List;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.helpers.RoomHelper;
import nl.tudelft.oopp.demo.entities.log.LogCollection;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidPasswordException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * The second version of the Room controller.
 */
@RestController("RoomV2")
@RequestMapping("api/v2/rooms")
@AllArgsConstructor
public class RoomControllerV2 {
    private final RoomService roomService;

    /**
     * Returns a list of all rooms.
     *
     * @return the list
     */
    @GetMapping
    public List<Room> findAll() {
        return roomService.findAll();
    }

    /**
     * Gets the room that has the passed id.
     *
     * @param id the id
     * @return the room
     */
    @GetMapping(value = "get")
    public Room getOne(@PathParam("id") long id) {
        return roomService.getOne(id);
    }

    /**
     * Gets a room's public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @GetMapping("public")
    public String getPublicPassword(@PathParam("roomId") long roomId) {
        return roomService.getPublicPassword(roomId);
    }

    /**
     * Gets a room's private password if the request comes from an ip registered as a moderator
     * in said room.
     *
     * @param roomId  the room id
     * @param request the request
     * @return the private password
     * @throws UnauthorizedException the unauthorized exception
     */
    @GetMapping("private")
    public String getPrivatePassword(@PathParam("roomId") long roomId,
                                     HttpServletRequest request) throws UnauthorizedException {

        return roomService.getPrivatePassword(roomId, request.getRemoteAddr());
    }

    /**
     * Gets a room's list of questions.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping(value = "questions")
    public Set<Question> findAllQuestions(@PathParam("roomId") long roomId) {
        return roomService.findAllQuestions(roomId);
    }

    /**
     * Gets a room's list of polls.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("polls")
    public Set<Poll> findAllPolls(@PathParam("roomId") long roomId) {
        return roomService.findAllPolls(roomId);
    }

    /**
     * Increments the tooFast attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/increment")
    public void incrementTooFast(@PathParam("roomId") long roomId) {
        roomService.incrementTooFast(roomId);
    }

    /**
     * Decrements the too the tooFast attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooFast/decrement")
    public void decrementTooFast(@PathParam("roomId") long roomId) {
        roomService.decrementTooFast(roomId);
    }

    /**
     * Increment the tooSlow attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/increment")
    public void incrementTooSlow(@PathParam("roomId") long roomId) {
        roomService.incrementTooSlow(roomId);
    }

    /**
     * Decrement the tooSlow attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/tooSlow/decrement")
    public void decrementTooSlow(@PathParam("roomId") long roomId) {
        roomService.decrementTooSlow(roomId);
    }

    /**
     * Increment the normalSpeed attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/normalSpeed/increment")
    public void incrementNormalSpeed(@PathParam("roomId") long roomId) {
        roomService.incrementNormalSpeed(roomId);
    }

    /**
     * Decrement the normalSpeed attribute of the room.
     *
     * @param roomId the room id
     */
    @PutMapping("/normalSpeed/decrement")
    public void decrementNormalSpeed(@PathParam("roomId") long roomId) {
        roomService.decrementNormalSpeed(roomId);
    }


    /**
     * Returns true if the user has been banned in the given room.
     *
     * @param roomId the room id
     * @param id     the id
     * @return the boolean
     */
    @GetMapping("isBanned")
    public boolean isBanned(@PathParam("roomId") long roomId,
                            @PathParam("id") long id) {
        return roomService.isBanned(roomId, id);
    }

    /**
     * Bans a user in the given room given the correct elevated password. Will throw an exception
     * if the request's IP is not registered as a moderator in the room.
     *
     * @param roomId           the room id
     * @param userId           the user id
     * @param elevatedPassword the elevated password
     * @param idToBeBanned     the to be banned id
     * @param request          the request
     * @throws UnauthorizedException the unauthorized exception
     */
    @PutMapping("ban")
    public void ban(@PathParam("roomId") Long roomId,
                    @PathParam("userId") Long userId,
                    @PathParam("elevatedPassword") String elevatedPassword,
                    @PathParam("idToBeBanned") Long idToBeBanned,
                    HttpServletRequest request)
            throws UnauthorizedException {
        roomService.banUser(roomId, userId, idToBeBanned, elevatedPassword);
    }

    /**
     * Unbans a user in the given room given the correct elevated password. Will throw an exception
     * if the request's IP is not registered as a moderator in the room.
     *
     * @param roomId           the room id
     * @param userId           the user id
     * @param elevatedPassword the elevated password
     * @param idToBeBanned     the id of the ip to ban
     * @param request          the request
     * @throws UnauthorizedException    the unauthorized exception
     * @throws InvalidPasswordException the invalid password exception
     */
    @PutMapping("unban")
    public void unban(@PathParam("roomId") long roomId,
                      @PathParam("userId") long userId,
                      @PathParam("elevatedPassword") String elevatedPassword,
                      @PathParam("idToBeBanned") long idToBeBanned,
                      HttpServletRequest request)
            throws UnauthorizedException, InvalidPasswordException {
        roomService.unbanUser(roomId, userId, idToBeBanned, elevatedPassword);
    }

    /**
     * Sets whether the room is ongoing or not.
     *
     * @param roomId    the room id
     * @param isOngoing the is ongoing
     * @param userId    the user id
     */
    @PutMapping("setOngoing")
    void setOngoing(@PathParam("roomId") long roomId,
                    @PathParam("isOngoing") boolean isOngoing,
                    @PathParam("userId") long userId) {
        roomService.setOngoing(roomId, isOngoing, userId);
    }

    /**
     * Exports the action log. Requires the request's IP to be registered as a moderator in the
     * room.
     *
     * @param roomId  the room id
     * @param request the request
     * @return the log collection
     */
    @GetMapping(value = "exportLog")
    public LogCollection exportLog(@PathParam("roomId") long roomId,
                                   HttpServletRequest request) {
        return roomService.exportLog(roomId, request.getRemoteAddr());
    }

    /**
     * Sets the student refresh rate. Requires the request's IP to be registered as a moderator in
     * the current room.
     *
     * @param roomId     the room id
     * @param roomConfig the room config
     * @param userId     the user id
     */
    @PutMapping("setConfig")
    public void setConfig(@PathParam("roomId") long roomId,
                          @RequestBody RoomConfig roomConfig,
                          @PathParam("userId") long userId) {
        roomService.setConfig(roomId, roomConfig, userId);
    }

    /**
     * Creates a new room.
     *
     * @param roomHelper the room helper
     * @param request    the request
     * @return the newly created room
     */
    @PostMapping("create")
    public Room createRoom(@RequestBody RoomHelper roomHelper,
                           HttpServletRequest request) {
        return roomService.createRoom(roomHelper, request.getRemoteAddr());
    }

    /**
     * Create a new user and have him join the room.
     *
     * @param password the room's password
     * @param username the user's username
     * @param request  the request
     * @return the user
     */
    @PutMapping("join")
    public User join(@PathParam("password") String password, @PathParam("username") String username,
                     HttpServletRequest request) {
        return roomService.join(password, username, request.getRemoteAddr());
    }

    /**
     * Gets the room given it's password.
     *
     * @param password the room's password
     * @return the room with that password
     */
    @GetMapping("getFromPass")
    public Room getRoom(@PathParam("password") String password) {
        return roomService.getRoom(password);
    }

    //    /**
    //     * Schedule a new room.
    //     *
    //     * @param username the admin's username
    //     * @param title    the title of the room
    //     * @param date     the starting date/time for the room
    //     * @param request  the request
    //     * @return the newly created room
    //     */
    //    @PutMapping("schedule")
    //    public Room scheduleRoom(@PathParam("username") String username,
    //                             @PathParam("title") String title,
    //                             @PathParam("date") long date, HttpServletRequest request) {
    //        return roomService.scheduleRoom(username, request.getRemoteAddr(), title, date);
    //    }
}
