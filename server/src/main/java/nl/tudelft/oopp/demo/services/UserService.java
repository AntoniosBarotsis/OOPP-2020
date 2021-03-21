package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
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

    public void addUpvotedQuestion(Long userId, Long questionId) {
        userRepository.addUpvotedQuestion(userId, questionId);
    }

    public void removeUpvotedQuestion(Long userId, Long questionId) {
        userRepository.removeUpvotedQuestion(userId,   questionId );
    }

    public HashSet<Question> getUpvotedQuestion(Long userId) {
        return userRepository.getUpvotedQuestion(userId);
    }
}
