package nl.tudelft.oopp.demo.entities.users;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
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
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.serializers.UserSerializer;

/**
 * The type User.
 */
@Entity
@Inheritance
@Data
@NoArgsConstructor
@JsonSerialize(using = UserSerializer.class)
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

    /**
     * Type toString.
     *
     * @return the string
     */
    public String typeToString() {
        if (type == Type.ADMIN) {
            return "LECTURER";
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
