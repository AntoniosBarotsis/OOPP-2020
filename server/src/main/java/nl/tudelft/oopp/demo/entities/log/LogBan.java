package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log ban. An [ELEVATED_USER] bans a [STUDENT] at [DATE]
 */
@NoArgsConstructor
@Entity(name = "LogBan")
@Table(name = "log_ban")
class LogBan extends LogEntry {
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "student")
    private Student student;

    /**
     * Instantiates a new Log ban.
     *
     * @param user    the user
     * @param student the student
     * @param date    the date
     */
    public LogBan(User user, Student student, Date date) {
        super(user, ActionType.BANNED, date);

        this.student = student;
    }

    /**
     * Instantiates a new Log ban.
     *
     * @param user    the user
     * @param student the student
     */
    public LogBan(User user, Student student) {
        super(user, ActionType.BANNED, new Date());

        this.student = student;
    }

    /**
     * Gets moderator.
     *
     * @return the moderator
     */
    public ElevatedUser getModerator() {
        return (ElevatedUser) super.getUser();
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public ActionType getActionType() {
        return ActionType.BANNED;
    }
}
