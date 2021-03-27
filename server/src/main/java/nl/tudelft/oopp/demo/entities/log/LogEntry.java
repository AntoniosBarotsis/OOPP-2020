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
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log entry. This abstract class represents any loggable event that might occur.
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
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room")
    private Room room;
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
     * @param room   the room
     * @param user   the user
     * @param action the action
     * @param date   the date
     */
    public LogEntry(Room room, User user, ActionType action, Date date) {
        this.room = room;
        this.user = user;
        this.action = action;
        this.date = date;
    }
}
