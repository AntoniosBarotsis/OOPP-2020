package nl.tudelft.oopp.demo.entities.log;

import java.util.ArrayList;
import java.util.Date;
import java.util.stream.Collectors;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Log.
 */
public class Log {
    private final ArrayList<LogEntry> events;

    /**
     * Instantiates a new Log.
     */
    public Log() {
        this.events = new ArrayList<>();
    }

    /**
     * Gets events.
     *
     * @return the events
     */
    public ArrayList<LogEntry> getEvents() {
        return events;
    }

    /**
     * Log ban.
     *
     * @param moderator the moderator
     * @param student   the student
     * @param date      the date
     */
    public void logBan(ElevatedUser moderator, Student student, Date date) {
        this.events.add(new LogBan(moderator, student, date));
    }

    /**
     * Log ban.
     *
     * @param moderator the moderator
     * @param student   the student
     */
    public void logBan(ElevatedUser moderator, Student student) {
        this.events.add(new LogBan(moderator, student));
    }

    /**
     * Log question.
     *
     * @param student  the student
     * @param question the question
     * @param date     the date
     */
    public void logQuestion(Student student, Question question, Date date) {
        this.events.add(new LogQuestion(student, question, date));
    }

    /**
     * Log question.
     *
     * @param student  the student
     * @param question the question
     */
    public void logQuestion(Student student, Question question) {
        this.events.add(new LogQuestion(student, question));
    }

    /**
     * Log join.
     *
     * @param user the user
     * @param room the room
     * @param date the date
     */
    public void logJoin(User user, Room room, Date date) {
        this.events.add(new LogJoin(user, room, date));
    }

    /**
     * Log join.
     *
     * @param user the user
     * @param room the room
     */
    public void logJoin(User user, Room room) {
        this.events.add(new LogJoin(user, room, new Date()));
    }

    /**
     * Filter events by user.
     *
     * @param user the user
     * @return the array list
     */
    public ArrayList<User> filterEventsByUser(User user) {
        return this.events.stream()
            .map(LogEntry::getUser)
            .filter(u -> u.equals(user))
            .collect(Collectors.toCollection(ArrayList<User>::new));
    }

    /**
     * Filter events by action.
     *
     * @param logAction the log action
     * @return the array list
     */
    public ArrayList<LogEntry> filterEventsByAction(LogAction logAction) {
        return this.events.stream()
            .filter(e -> e.getAction().equals(logAction))
            .collect(Collectors.toCollection(ArrayList::new));
    }
}
