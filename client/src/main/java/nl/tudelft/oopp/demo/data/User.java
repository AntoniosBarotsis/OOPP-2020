package nl.tudelft.oopp.demo.data;

import java.util.Set;

public class User {
    private String username;
    private Set<Question> questionsAsked;
    private Set<Question> questionsUpvoted;

    /** Initializes a user
     * @param username username of user
     * @param questionsAsked set of questions asked
     * @param questionsUpvoted set of questions upvoted
     */
    public User(String username, Set<Question> questionsAsked, Set<Question> questionsUpvoted) {
        this.username = username;
        this.questionsAsked = questionsAsked;
        this.questionsUpvoted = questionsUpvoted;
    }

    /** Getter for username
     * @return username of user
     */
    public String getUsername() {
        return username;
    }

    /** Setter for username
     * @param username username of user
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Getter for asked questions by user
     * @return set of asked questions by user
     */
    public Set<Question> getQuestionsAsked() {
        return questionsAsked;
    }

    /** Getter for upvoted questions by user
     * @return set of upvoted questions by user
     */
    public Set<Question> getQuestionsUpvoted() {
        return questionsUpvoted;
    }


}
