package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;

/**
 * The type Log question. A [USER] asks a [QUESTION] at [DATE]
 */
class LogQuestion extends LogEntry {
    /**
     * Instantiates a new Log question.
     *
     * @param student     the user
     * @param question the question
     * @param date     the date
     */
    public LogQuestion(Student student, Question question, Date date) {
        super(student, new LogAction(LogAction.ActionType.ASKED, question), date);
    }

    /**
     * Instantiates a new Log question.
     *
     * @param student     the user
     * @param question the question
     */
    public LogQuestion(Student student, Question question) {
        super(student, new LogAction(LogAction.ActionType.ASKED, question), new Date());
    }


    /**
     * Gets student.
     *
     * @return the student
     */
    public Student getStudent() {
        return (Student) super.getUser();
    }

    @Override
    public LogAction.ActionType getActionType() {
        return LogAction.ActionType.ASKED;
    }

    /**
     * Gets question.
     *
     * @return the question
     */
    public Question getQuestion() {
        return (Question) super.getAction().getObject();
    }
}
