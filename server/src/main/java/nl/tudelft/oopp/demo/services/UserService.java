package nl.tudelft.oopp.demo.services;

import lombok.AllArgsConstructor;
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
     * @return boolean
     */
    public boolean isInvalidAuthorId(User author) {
        if (userRepository.findById(author.getId()).isPresent()) {
            return !author.equals(userRepository.findById(author.getId()).get());
        } else {
            return true;
        }
    }
}
