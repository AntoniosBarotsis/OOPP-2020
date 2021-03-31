package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import java.util.List;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * The type Mc question.
 */
@Entity(name = "Poll")
@Table(name = "polls")
@Data
@NoArgsConstructor
public class Poll {
    @Id
    @SequenceGenerator(
        name = "poll_sequence",
        sequenceName = "poll_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "poll_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "text")
    private String text;
    @Column(name = "timeCreated")
    private Date timeCreated;
    @ElementCollection
    @Column(name = "options")
    private List<String> options;
    @ElementCollection
    @Column(name = "correct_answer")
    private List<String> correctAnswer;
    @Column(name = "status")
    private Poll.PollStatus status;

    /**
     * Instantiates a new Poll.
     *
     * @param text          the text
     * @param options       the options
     * @param correctAnswer the correct answer
     */
    public Poll(String text, List<String> options,
                List<String> correctAnswer) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;

        this.timeCreated = new Date();
        this.status = PollStatus.OPEN;
    }

    public enum PollStatus {
        /**
         * Open poll status.
         */
        OPEN,
        /**
         * Closed poll status.
         */
        CLOSED,
        /**
         * Statistics poll status.
         */
        STATISTICS
    }
}