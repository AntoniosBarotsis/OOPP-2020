package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import java.util.Date;

import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

/**
 * The type Mc question.
 */
@Entity(name = "Poll")
@Table(name = "polls")
public class Poll {
    @Id
    @SequenceGenerator(
        name = "question_sequence",
        sequenceName = "poll_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "poll_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "title")
    private String title;
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
     * @param title         the title
     * @param text          the text
     * @param options       the options
     * @param correctAnswer the correct answer
     */
    public Poll(String title, String text, List<String> options,
                List<String> correctAnswer) {
        this.title = title;
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;

        this.timeCreated = new Date();
        this.status = PollStatus.OPEN;
    }

    /**
     * Instantiates a new Mc question.
     */
    public Poll() {

    }

    /**
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets text.
     *
     * @return the text
     */
    public String getText() {
        return text;
    }

    /**
     * Sets text.
     *
     * @param text the text
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Gets time created.
     *
     * @return the time created
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Sets time created.
     *
     * @param timeCreated the time created
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * Gets options.
     *
     * @return the options
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Sets options.
     *
     * @param options the options
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Gets correct answer.
     *
     * @return the correct answer
     */
    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets correct answer.
     *
     * @param correctAnswer the correct answer
     */
    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Gets status.
     *
     * @return the status
     */
    public PollStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(PollStatus status) {
        this.status = status;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Poll)) {
            return false;
        }
        Poll poll = (Poll) o;
        return id == poll.id && Objects.equals(title, poll.title)
            && Objects.equals(text, poll.text)
            && Objects.equals(timeCreated, poll.timeCreated)
            && Objects.equals(options, poll.options)
            && Objects.equals(correctAnswer, poll.correctAnswer)
            && status == poll.status;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, title, text, timeCreated, options, correctAnswer, status);
    }

    @Override
    public String toString() {
        return "Poll{"
            + "id=" + id
            + ", title='" + title + '\''
            + ", text='" + text + '\''
            + ", timeCreated=" + timeCreated
            + ", options=" + options
            + ", correctAnswer=" + correctAnswer
            + ", status=" + status
            + '}';
    }

    private enum PollStatus {
        /**
         * Open poll status.
         */
        OPEN,
        /**
         * Closed poll status.
         */
        CLOSED
    }
}