package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;

import java.util.Date;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Poll;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidPasswordException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.services.RoomService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

/**
 * The type Room controller.
 */
@RestController("RoomV2")
@RequestMapping("api/v2/rooms")
@AllArgsConstructor
public class RoomControllerV2 {
    private final RoomService roomService;

    /**
     * Find all list.
     *
     * @return the list
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll() throws JsonProcessingException {
        return roomService.findAll();
    }

    /**
     * Gets one.
     *
     * @param id the id
     * @return the one
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(value = "get", produces = MediaType.APPLICATION_JSON_VALUE)
    public String getOne(@PathParam("id") long id) throws JsonProcessingException {
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
     * Find all questions set.
     *
     * @param roomId the room id
     * @return the set
     * @throws JsonProcessingException the json processing exception
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

    /**
     * Increment normal speed.
     *
     * @param roomId the room id
     */
    @PutMapping("/normalSpeed/increment")
    public void incrementNormalSpeed(@PathParam("roomId") long roomId) {
        roomService.incrementNormalSpeed(roomId);
    }

    /**
     * Decrement normal speed.
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
     * @param roomId  the room id
     * @param request the request
     * @return the boolean
     */
    @GetMapping("isBanned")
    public boolean isBanned(@PathParam("roomId") long roomId,
                            HttpServletRequest request) {
        return roomService.isBanned(roomId, request.getRemoteAddr());
    }

    /**
     * Bans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param userId           the user id
     * @param elevatedPassword the elevated password
     * @param request          the request
     * @throws UnauthorizedException the unauthorized exception
     */
    @PutMapping("ban")
    public void ban(@PathParam("roomId") long roomId,
                    @PathParam("userId") long userId,
                    @PathParam("elevatedPassword") String elevatedPassword,
                    HttpServletRequest request)
            throws UnauthorizedException {
        roomService.banUser(roomId, userId, request.getRemoteAddr(), elevatedPassword);
    }

    /**
     * Unbans a user in the given room given the correct elevated password.
     *
     * @param roomId           the room id
     * @param userId           the user id
     * @param elevatedPassword the elevated password
     * @param request          the request
     * @throws UnauthorizedException    the unauthorized exception
     * @throws InvalidPasswordException the invalid password exception
     */
    @PutMapping("unban")
    public void unban(@PathParam("roomId") long roomId,
                      @PathParam("userId") long userId,
                      @PathParam("elevatedPassword") String elevatedPassword,
                      HttpServletRequest request)
            throws UnauthorizedException, InvalidPasswordException {
        roomService.unbanUser(roomId, userId, request.getRemoteAddr(), elevatedPassword);
    }

    /**
     * Sets room to ongoing or not.
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
     * Export log string.
     *
     * @param roomId  the room id
     * @param request the request
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(value = "exportLog", produces = MediaType.APPLICATION_JSON_VALUE)
    public String exportLog(@PathParam("roomId") long roomId,
                            HttpServletRequest request)
            throws JsonProcessingException {
        return roomService.exportLog(roomId, request.getRemoteAddr());
    }

    /**
     * Sets student refresh rate.
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
     * Create a new room.
     *
     * @param username the admin's username
     * @param title    the title of the room
     * @param request  the request
     * @return the newly created room
     */
    @GetMapping("create")
    public Room createRoom(@PathParam("username") String username, @PathParam("title") String title,
                           HttpServletRequest request) {
        return roomService.createRoom(username, request.getRemoteAddr(), title);
    }

    /**
     * Create a new user and have him join the room.
     *
     * @param password the room's password
     * @param username the user's username
     * @param request  the request
     * @return the user
     */
    @GetMapping("join")
    public User join(@PathParam("password") String password, @PathParam("username") String username,
                     HttpServletRequest request) {
        return roomService.join(password, username, request.getRemoteAddr());
    }

    /**
     * Get the room.
     *
     * @param password the room's password
     * @return the room with that password
     */
    @GetMapping("getFromPass")
    public Room getRoom(@PathParam("password") String password) {
        return roomService.getRoom(password);
    }

    /**
     * Schedule a new room.
     *
     * @param username the admin's username
     * @param title    the title of the room
     * @param date     the starting date/time for the room
     * @param request  the request
     * @return the newly created room
     */
    @PutMapping("schedule")
    public Room scheduleRoom(@PathParam("username") String username, @PathParam("title") String title,
                             @PathParam("date") long date, HttpServletRequest request) {
        return roomService.scheduleRoom(username, request.getRemoteAddr(), title, date);
    }
}
