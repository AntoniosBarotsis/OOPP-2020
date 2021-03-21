package nl.tudelft.oopp.demo.controllers;

import com.fasterxml.jackson.core.JsonProcessingException;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.helpers.StudentHelper;
import nl.tudelft.oopp.demo.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.websocket.server.PathParam;
import java.util.HashSet;
import java.util.Set;

/**
 * The type User controller.
 */
@RestController("User")
@RequestMapping("api/v1/users")
@AllArgsConstructor
public class UserController {
    private final UserService userService;

    /**
     * Find all string.
     *
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    public String findAll() throws JsonProcessingException {
        return userService.findAll();
    }

    /**
     * Add long.
     *
     * @param user the user
     * @return the long
     */
    @PostMapping("add")
    public long add(@RequestBody StudentHelper user) {
        return userService.add(user.createStudent());
    }


    @GetMapping(value = "addUpvotedQuestion")
    public void addUpvotedQuestion(@PathParam("userId") Long userId, @PathParam("questionId") Long questionId){
        userService.addUpvotedQuestion(userId, questionId);

    }

    @GetMapping(value = "removeUpvotedQuestion")
    public void removeUpvotedQuestion(@PathParam("userId") Long userId, @PathParam("questionId") Long questionId){
        userService.removeUpvotedQuestion(userId, questionId);

    }
    @GetMapping(value = "getUpvotedQuestion")
    public HashSet<Question> getUpvotedQuestion(@PathParam("userId") Long userId){
        return userService.getUpvotedQuestion(userId);

    }
}
