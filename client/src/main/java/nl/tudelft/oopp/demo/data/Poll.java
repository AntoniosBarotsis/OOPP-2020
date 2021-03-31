package nl.tudelft.oopp.demo.data;

import com.google.gson.annotations.JsonAdapter;
import java.util.Date;
import java.util.List;
import nl.tudelft.oopp.demo.data.deserializers.PollInstanceCreator;

@JsonAdapter(PollInstanceCreator.class)
public class Poll {
    private long id;
    private String text;
    private Date timeCreated;
    private List<String> options;
    private List<String> correctAnswer;
    private Poll.PollStatus status;

    /**
     * Initializes a new poll.
     * @param id id of poll
     * @param text question of poll
     * @param timeCreated creation time of poll
     * @param options options of poll
     * @param correctAnswer correct answers of poll
     * @param status status of poll
     */
    public Poll(long id, String text, Date timeCreated, List<String> options,
                List<String> correctAnswer, PollStatus status) {
        this.id = id;
        this.text = text;
        this.timeCreated = timeCreated;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.status = status;
    }

    public Poll(String text, List<String> options, List<String> correctAnswer, Poll.PollStatus status) {
        this.text = text;
        this.options = options;
        this.correctAnswer = correctAnswer;
        this.timeCreated = new Date();
        this.status = status;
    }

    /**
     * Getter for id of poll.
     * @return id of poll
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id of poll.
     * @param id id of poll
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for question of poll.
     * @return question of poll
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for question of poll.
     * @param text question of poll
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for creation time of poll.
     * @return creation time of poll
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Setter for creation time of poll.
     * @param timeCreated creation time of poll
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * Getter for options of poll.
     * @return options of poll
     */
    public List<String> getOptions() {
        return options;
    }

    /**
     * Setter for options of poll.
     * @param options options of poll
     */
    public void setOptions(List<String> options) {
        this.options = options;
    }

    /**
     * Getter for correct answers of poll.
     * @return correct answers of poll
     */
    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Setter for correct answers of poll.
     * @param correctAnswer correct answers of poll
     */
    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    /**
     * Getter for status of poll.
     * @return status of poll
     */
    public PollStatus getStatus() {
        return status;
    }

    /**
     * Setter for status of poll.
     * @param status options of poll
     */
    public void setStatus(PollStatus status) {
        this.status = status;
    }

    /**
     * Poll enum status.
     */
    public enum PollStatus {
        /** Open poll status.
         */
        OPEN,
        /** Closed poll status.
         */
        CLOSED,
        /** Statistics poll status.
         */
        STATISTICS
    }
}
