package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.Entity;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log join. A [USER] joins a [ROOM]
 */
@NoArgsConstructor
@Entity(name = "LogJoin")
@Table(name = "log_join")
@Getter
@Setter
public class LogJoin extends LogEntry {

    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     * @param date the date
     */
    public LogJoin(User user, Room room, Date date) {
        super(room, user, ActionType.JOINED, date);
    }

    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     */
    public LogJoin(User user, Room room) {
        super(room, user, ActionType.JOINED, new Date());
    }
}
