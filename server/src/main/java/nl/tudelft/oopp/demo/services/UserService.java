package nl.tudelft.oopp.demo.services;

import java.util.HashSet;
import java.util.List;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
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
     * Adds a user.
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
     * Finds all users.
     *
     * @return the list
     */
    public List<User> findAll() {
        return userRepository.findAll();
    }

    /**
     * Adds questionId to the ids of question user with userId upvoted,
     * if he did not upvote it before.
     *
     * @param userId     the user id
     * @param questionId the question id
     */
    public void addUpvotedQuestion(Long userId, Long questionId) {
        HashSet<Question> set = userRepository.getUpvotedQuestion(userId);
        boolean contains = false;

        for (Question q: set) {
            if (q.getId() == questionId) {
                contains = true;
            }
        }
        if (!contains) {
            userRepository.addUpvotedQuestion(userId, questionId);
        }
    }

    /**
     * Removes questionId from the ids of questions user with userId upvoted,
     * if user upvoted the question before.
     *
     * @param userId     the user id
     * @param questionId the question id
     */
    public void removeUpvotedQuestion(Long userId, Long questionId) {
        HashSet<Question> set = userRepository.getUpvotedQuestion(userId);
        boolean contains = false;

        for (Question q: set) {
            if (q.getId() == questionId) {
                contains = true;
            }
        }
        if (contains) {
            userRepository.removeUpvotedQuestion(userId, questionId);
        }

    }


    /**
     * Gets the set of questions that have been upvoted by the user.
     *
     * @param userId the user id
     * @return the upvoted question
     */
    public HashSet<Question> getUpvotedQuestion(Long userId) {
        return userRepository.getUpvotedQuestion(userId);
    }

    /**
     * Get all students.
     *
     * @return json representation of all students
     */
    public List<User> findAllStudents() {
        return userRepository.findAllStudents();
    }

    /**
     * Get all elevated users.
     *
     * @return json representation of all elevated users
     */
    public List<User> findAllElevatedUsers() {
        return userRepository.findAllElevateUsers();
    }

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