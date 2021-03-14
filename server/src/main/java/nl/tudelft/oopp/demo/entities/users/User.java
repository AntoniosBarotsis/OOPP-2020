package nl.tudelft.oopp.demo.entities.users;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import nl.tudelft.oopp.demo.entities.Question;

/**
 * The type User.
 */
@Entity
@Inheritance
public abstract class User {
    @Id
    @SequenceGenerator(
        name = "user_sequence",
        sequenceName = "user_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "user_sequence"
    )
    @Column(name = "id")
    private long id;
    @Column(name = "username")
    private String username;
    @JsonIgnore
    @Column(name = "ip")
    private String ip;
    @OneToMany
    @Column(name = "questions_asked")
    private Set<Question> questionsAsked;
    @OneToMany
    @Column(name = "questions_upvoted")
    private Set<Question> questionsUpvoted;
    @Column(name = "type")
    private Type type;

    /**
     * Instantiates a new User.
     *
     * @param username the username
     * @param ip       the ip
     */
    public User(String username, String ip) {
        this.username = username;
        this.ip = ip;

        this.questionsAsked = new HashSet<>();
        this.questionsUpvoted = new HashSet<>();
    }

    /**
     * Instantiates a new User.
     */
    public User() {
    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets username.
     *
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Sets username.
     *
     * @param username the username
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Gets ip.
     *
     * @return the ip
     */
    public String getIp() {
        return ip;
    }

    /**
     * Sets ip.
     *
     * @param ip the ip
     */
    public void setIp(String ip) {
        this.ip = ip;
    }

    /**
     * Gets questions asked.
     *
     * @return the questions asked
     */
    public Set<Question> getQuestionsAsked() {
        return questionsAsked;
    }

    /**
     * Sets questions asked.
     *
     * @param questionsAsked the questions asked
     */
    public void setQuestionsAsked(Set<Question> questionsAsked) {
        this.questionsAsked = questionsAsked;
    }

    /**
     * Gets questions upvoted.
     *
     * @return the questions upvoted
     */
    public Set<Question> getQuestionsUpvoted() {
        return questionsUpvoted;
    }

    /**
     * Sets questions upvoted.
     *
     * @param questionsUpvoted the questions upvoted
     */
    public void setQuestionsUpvoted(Set<Question> questionsUpvoted) {
        this.questionsUpvoted = questionsUpvoted;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof User)) {
            return false;
        }
        User user = (User) o;
        return id == user.id
            && Objects.equals(username, user.username)
            && Objects.equals(ip, user.ip)
            && type == user.type;
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, username, ip, type);
    }

    @Override
    public String toString() {
        return "User{"
            + "id=" + id
            + ", username='" + username + '\''
            + ", ip='" + ip + '\''
            + ", questionsAsked=" + questionsAsked
            + ", questionsUpvoted=" + questionsUpvoted
            + ", type=" + type
            + '}';
    }

    /**
     * Type toString.
     *
     * @return the string
     */
    public String typeToString() {
        if (type == Type.ADMIN) {
            return "ADMIN";
        } else if (type == Type.MODERATOR) {
            return "MODERATOR";
        } else {
            return "STUDENT";
        }
    }

    /**
     * The enum Type.
     */
    protected enum Type {
        /**
         * Student type.
         */
        STUDENT,
        /**
         * Moderator type.
         */
        MODERATOR,
        /**
         * Admin type.
         */
        ADMIN
    }
}
