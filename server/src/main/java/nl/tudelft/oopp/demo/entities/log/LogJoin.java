package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log join.
 */
class LogJoin extends LogEntry{
    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     * @param date the date
     */
    public LogJoin(User user, Room room, Date date) {
        super(user, new LogAction(LogAction.ActionType.JOINED, room), date);
    }

    /**
     * Instantiates a new Log join.
     *
     * @param user the user
     * @param room the room
     */
    public LogJoin(User user, Room room) {
        super(user, new LogAction(LogAction.ActionType.JOINED, room), new Date());
    }

    @Override
    public LogAction.ActionType getActionType() {
        return LogAction.ActionType.JOINED;
    }

    /**
     * Gets room.
     *
     * @return the room
     */
    public Room getRoom() {
        return (Room) super.getAction().getObject();
    }
}
