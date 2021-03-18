package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log join. A [USER] joins a [ROOM]
 */
@Entity
@NoArgsConstructor
public class LogJoin extends LogEntry {
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room")
    private Room room;

    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     * @param date the date
     */
    public LogJoin(User user, Room room, Date date) {
        super(user, ActionType.JOINED, date);

        this.room = room;
    }

    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     */
    public LogJoin(User user, Room room) {
        super(user, ActionType.JOINED, new Date());

        this.room = room;
    }

    /**
     * Gets user.
     *
     * @return the moderator
     */
    public User getUser() {
        return super.getUser();
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public ActionType getActionType() {
        return ActionType.JOINED;
    }
}
