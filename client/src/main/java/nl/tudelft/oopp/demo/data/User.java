package nl.tudelft.oopp.demo.data;

import java.util.Set;

public class User {
    private long id;
    private UserType userType;
    private String username;
    private Set<Question> questionsAsked;
    private Set<Question> questionsUpvoted;

    /**
     * Initializes a user.
     * @param id id of user
     * @param username username of user
     * @param questionsAsked set of questions asked
     * @param questionsUpvoted set of questions upvoted
     */
    public User(long id, UserType userType, String username,
                Set<Question> questionsAsked, Set<Question> questionsUpvoted) {
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
    public Set<Question> getQuestionsAsked() {
        return questionsAsked;
    }

    /**
     * Getter for upvoted questions by user.
     * @return set of upvoted questions by user
     */
    public Set<Question> getQuestionsUpvoted() {
        return questionsUpvoted;
    }

    /**
     * Enum for type of user.
     */
    private enum UserType {
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
