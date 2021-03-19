package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Collection;
import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.serializers.UserSerializer;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type User service.
 */
@Service
@AllArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    /**
     * Add long.
     *
     * @param user the user
     * @return the long
     */
    public long add(User user) {
        return userRepository.save(user).getId();
    }

    /**
     * Returns true if the author does not exist in the database or if the id poitns to a
     * different user.
     *
     * @param author the author
     * @return boolean boolean
     */
    public boolean isInvalidAuthorId(User author) {
        if (userRepository.findById(author.getId()).isPresent()) {
            return !author.equals(userRepository.findById(author.getId()).get());
        } else {
            return true;
        }
    }

    /**
     * Find all string.
     *
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String findAll() throws JsonProcessingException {
        return mapUser(userRepository.findAll());
    }

    /**
     * Map user string.
     *
     * @param users the users
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String mapUser(Collection<User> users) throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();
        SimpleModule module = new SimpleModule();
        module.addSerializer(User.class, new UserSerializer());
        objMapper.registerModule(module);

        return objMapper.writeValueAsString(users);
    }

    /**
     * Get all students.
     *
     * @return json representation of all students
     * @throws JsonProcessingException the json processing exception
     */
    public String findAllStudents() throws JsonProcessingException {
        return mapUser(userRepository.findAllStudents());
    }

    /**
     * Get all elevated users.
     *
     * @return json representation of all elevated users
     * @throws JsonProcessingException the json processing exception
     */
    public String findAllElevatedUsers() throws JsonProcessingException {
        return mapUser(userRepository.findAllElevateUsers());
    }

    // TODO Error handling

    /**
     * Get an elevated user using their id.
     *
     * @param userId the user's id
     * @return the elevated user
     */
    public User getElevated(long userId) {
        User returnUser = null;
        for (User user : userRepository.findAllElevateUsers()) {
            if (user.getId() == userId) {
                returnUser = user;
                break;
            }
        }
        return returnUser;
    }

    /**
     * Get a student using their id.
     *
     * @param userId the user's id
     * @return the student
     */
    public User getStudent(long userId) {
        User returnUser = null;
        for (User user : userRepository.findAllStudents()) {
            if (user.getId() == userId) {
                returnUser = user;
                break;
            }
        }
        return returnUser;
    }
}