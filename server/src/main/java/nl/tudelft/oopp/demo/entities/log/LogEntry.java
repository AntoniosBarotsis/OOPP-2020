package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log entry.
 */
abstract class LogEntry {
    private User user;
    private LogAction action;
    private Date date;

    /**
     * Instantiates a new Log entry.
     *
     * @param user   the user
     * @param action the action
     * @param date   the date
     */
    public LogEntry(User user, LogAction action, Date date) {
        this.user = user;
        this.action = action;
        this.date = date;
    }

    /**
     * Gets user.
     *
     * @return the user
     */
    public User getUser() {
        return user;
    }

    /**
     * Sets user.
     *
     * @param user the user
     */
    public void setUser(User user) {
        this.user = user;
    }

    /**
     * Gets action.
     *
     * @return the action
     */
    public LogAction getAction() {
        return action;
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public LogAction.ActionType getActionType() {
        return action.getType();
    }

    /**
     * Sets action.
     *
     * @param action the action
     */
    public void setAction(LogAction action) {
        this.action = action;
    }

    /**
     * Gets date.
     *
     * @return the date
     */
    public Date getDate() {
        return date;
    }

    /**
     * Sets date.
     *
     * @param date the date
     */
    public void setDate(Date date) {
        this.date = date;
    }
}
