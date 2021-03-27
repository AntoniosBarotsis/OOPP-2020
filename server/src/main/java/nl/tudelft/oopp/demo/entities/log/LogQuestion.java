package nl.tudelft.oopp.demo.entities.log;

import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.users.Student;

/**
 * The type Log question. A [USER] asks a [QUESTION].
 */
@NoArgsConstructor
@Entity(name = "LogQuestion")
@Table(name = "log_question")
@Getter
@Setter
public class LogQuestion extends LogEntry {
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "question")
    private Question question;

    /**
     * Instantiates a new Log question.
     *
     * @param room     the room
     * @param student  the user
     * @param question the question
     * @param date     the date
     */
    public LogQuestion(Room room, Student student, Question question, Date date) {
        super(room, student, ActionType.ASKED, date);

        this.question = question;
    }

    /**
     * Instantiates a new Log question.
     *
     * @param room     the room
     * @param student  the user
     * @param question the question
     */
    public LogQuestion(Room room, Student student, Question question) {
        super(room, student, ActionType.ASKED, new Date());

        this.question = question;
    }

}
