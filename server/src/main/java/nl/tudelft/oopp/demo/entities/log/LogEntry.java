package nl.tudelft.oopp.demo.entities.log;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log entry.
 */
@Data
@Inheritance(strategy = InheritanceType.TABLE_PER_CLASS)
@NoArgsConstructor
@Entity(name = "LogEntry")
public abstract class LogEntry {
    @Id
    @SequenceGenerator(
        name = "LogEntry_sequence",
        sequenceName = "LogEntry_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "LogEntry_sequence"
    )
    private long id;
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "user")
    private User user;
    @Column(name = "type")
    private ActionType action;
    @Column(name = "date")
    private Date date;

    /**
     * The enum Action type.
     */
    public enum ActionType {
        /**
         * Banned action type.
         */
        BANNED,
        /**
         * Asked action type.
         */
        ASKED,
        /**
         * Joined action type.
         */
        JOINED
    }

    /**
     * Instantiates a new Log entry.
     *
     * @param user   the user
     * @param action the action
     * @param date   the date
     */
    public LogEntry(User user, ActionType action, Date date) {
        this.user = user;
        this.action = action;
        this.date = date;
    }
}
