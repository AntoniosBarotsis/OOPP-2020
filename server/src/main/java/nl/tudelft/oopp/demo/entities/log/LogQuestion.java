package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;

/**
 * The type Log question. A [USER] asks a [QUESTION] at [DATE]
 */
@NoArgsConstructor
@Entity(name = "LogQuestion")
@Table(name = "log_question")
class LogQuestion extends LogEntry {
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "question")
    private Question question;

    /**
     * Instantiates a new Log question.
     *
     * @param student  the user
     * @param question the question
     * @param date     the date
     */
    public LogQuestion(Student student, Question question, Date date) {
        super(student, ActionType.ASKED, date);

        this.question = question;
    }

    /**
     * Instantiates a new Log question.
     *
     * @param student  the user
     * @param question the question
     */
    public LogQuestion(Student student, Question question) {
        super(student, ActionType.ASKED, new Date());

        this.question = question;
    }


    /**
     * Gets student.
     *
     * @return the student
     */
    public Student getStudent() {
        return (Student) super.getUser();
    }

    /**
     * Gets action type.
     *
     * @return the action type
     */
    public ActionType getActionType() {
        return ActionType.ASKED;
    }
}
