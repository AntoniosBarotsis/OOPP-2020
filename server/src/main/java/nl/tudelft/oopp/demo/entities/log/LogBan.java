package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log ban. An [ELEVATED_USER] bans an [IP] at [DATE]
 */
@NoArgsConstructor
@Entity(name = "LogBan")
@Table(name = "log_ban")
public class LogBan extends LogEntry {
    @Column(name = "ip")
    private String ip;

    /**
     * Instantiates a new Log ban.
     *
     * @param user    the user
     * @param ip      the ip
     * @param date    the date
     */
    public LogBan(User user, String ip, Date date) {
        super(user, ActionType.BANNED, date);

        this.ip = ip;
    }

    /**
     * Instantiates a new Log ban.
     *
     * @param user    the user
     * @param student the student
     */
    public LogBan(User user, String ip) {
        super(user, ActionType.BANNED, new Date());

        this.ip = ip;
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
