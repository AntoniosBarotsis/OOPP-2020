package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;

/**
 * The type Log ban. An [ELEVATED_USER] bans a [STUDENT] at [DATE]
 */
class LogBan extends LogEntry {

    /**
     * Instantiates a new Log ban.
     *
     * @param moderator the moderator
     * @param student   the student
     * @param date      the date
     */
    public LogBan(ElevatedUser moderator, Student student, Date date) {
        super(moderator, new LogAction(LogAction.ActionType.BANNED, student), date);
    }

    /**
     * Instantiates a new Log ban.
     *
     * @param moderator the moderator
     * @param student   the student
     */
    public LogBan(ElevatedUser moderator, Student student) {
        super(moderator, new LogAction(LogAction.ActionType.BANNED, student), new Date());
    }

    /**
     * Gets moderator.
     *
     * @return the moderator
     */
    public ElevatedUser getModerator() {
        return (ElevatedUser) super.getUser();
    }

    @Override
    public LogAction.ActionType getActionType() {
        return LogAction.ActionType.BANNED;
    }

    /**
     * Gets student.
     *
     * @return the student
     */
    public Student getStudent() {
        return (Student) super.getAction().getObject();
    }
}
