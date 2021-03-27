package nl.tudelft.oopp.demo.data;

import com.google.gson.annotations.JsonAdapter;
import java.util.Set;
import nl.tudelft.oopp.demo.data.deserializers.UserInstanceCreator;

@JsonAdapter(UserInstanceCreator.class)
public class User {
    private long id;
    private String username;
    private Set<Long> questionsAsked;
    private Set<Long> questionsUpvoted;
    private UserType userType;

    /**
     * Initializes a user.
     * @param id id of user
     * @param username username of user
     * @param questionsAsked set of questions asked
     * @param questionsUpvoted set of questions upvoted
     */
    public User(long id, String username, Set<Long> questionsAsked,
                Set<Long> questionsUpvoted,  UserType userType) {
        this.id = id;
        this.userType = userType;
        this.username = username;
        this.questionsAsked = questionsAsked;
        this.questionsUpvoted = questionsUpvoted;
    }

    /**
     * Getter for id of user.
     * @return id of user
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id of user.
     * @param id id of user
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for type of user.
     * @return type of user
     */
    public UserType getUserType() {
        return userType;
    }

    /**
     * Setter for type of user.
     * @param userType type of user
     */
    public void setUserType(UserType userType) {
        this.userType = userType;
    }

    /**
     * Getter for username.
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for username.
     * @param username username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for asked questions by user.
     * @return set of asked questions by user
     */
    public Set<Long> getQuestionsAsked() {
        return questionsAsked;
    }

    /**
     * Getter for upvoted questions by user.
     * @return set of upvoted questions by user
     */
    public Set<Long> getQuestionsUpvoted() {
        return questionsUpvoted;
    }

    /**
     * Adds a question id to questionsUpvoted.
     *
     * @param questionId the question id
     */
    public void addQuestionUpvoted(Long questionId) {
        questionsUpvoted.add(questionId);
    }

    /**
     * Removes a question id from questionsUpvoted.
     *
     * @param questionId the question id
     */
    public void removeQuestionUpvoted(Long questionId) {
        questionsUpvoted.remove(questionId);
    }

    /**
     * Enum for type of user.
     */
    public enum UserType {
        /**
         * Student type.
         */
        STUDENT,
        /**
         * Moderator type.
         */
        MODERATOR,
        /**
         * Lecturer type.
         */
        LECTURER
    }
}
