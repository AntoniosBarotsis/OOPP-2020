package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log ban. An [ELEVATED_USER] bans an [IP].
 */
@NoArgsConstructor
@Entity(name = "LogBan")
@Table(name = "log_ban")
@Getter
@Setter
public class LogBan extends LogEntry {
    @Column(name = "ip")
    private String ip;

    /**
     * Instantiates a new Log ban.
     *
     * @param room the room
     * @param user the user
     * @param ip   the ip
     * @param date the date
     */
    public LogBan(Room room, User user, String ip, Date date) {
        super(room, user, ActionType.BANNED, date);

        this.ip = ip;
    }

    /**
     * Instantiates a new Log ban.
     *
     * @param room the room
     * @param user the user
     * @param ip   the ip
     */
    public LogBan(Room room, User user, String ip) {
        super(room, user, ActionType.BANNED, new Date());

        this.ip = ip;
    }
}
