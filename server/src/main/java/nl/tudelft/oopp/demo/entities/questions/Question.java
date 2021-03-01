package nl.tudelft.oopp.demo.entities.questions;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.util.Date;
import java.util.Objects;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The Question abstract class.
 */
@Entity
@Inheritance
public abstract class Question {
    /**
     * The Type.
     */
    @Column(name = "type")
    protected Type type;
    @Id
    @SequenceGenerator(
        name = "question_sequence",
        sequenceName = "question_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "question_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "text")
    private String text;
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "upvotes")
    private int upvotes;
    @Column(name = "score")
    private int score;
    @Column(name = "timeCreated")
    private Date timeCreated;
    @Column(name = "status")
    private QuestionStatus status;
    @Column(name = "answer")
    private String answer;

    /**
     * Instantiates a new Question.
     *
     * @param title       the title
     * @param text        the text
     * @param author      the author
     * @param timeCreated the time created
     */
    public Question(String title, String text, User author, Date timeCreated) {
        this.title = title;
        this.text = text;
        this.author = author;
        this.timeCreated = timeCreated;

        this.upvotes = 0;
        this.score = 0;
        this.status = QuestionStatus.OPEN;
        this.answer = "";
    }

    /**
     * Instantiates a new Question.
     */
    public Question() {

    }

    /**
     * Export question to txt string.
     *
     * @return the string
     */
    public String exportToTxt() {
        return "TO BE IMPLEMENTED";
    }

    /**
     * Export question to json string.
     *
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String exportToJson() throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();

        return objMapper.writeValueAsString(this);
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public Type getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(Type type) {
        this.type = type;
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
     * Gets author.
     *
     * @return the author
     */
    public User getAuthor() {
        return author;
    }

    /**
     * Sets author.
     *
     * @param author the author
     */
    public void setAuthor(User author) {
        this.author = author;
    }

    /**
     * Gets upvotes.
     *
     * @return the upvotes
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Sets upvotes.
     *
     * @param upvotes the upvotes
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Gets score.
     *
     * @return the score
     */
    public int getScore() {
        return score;
    }

    /**
     * Sets score.
     *
     * @param score the score
     */
    public void setScore(int score) {
        this.score = score;
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
     * Gets status.
     *
     * @return the status
     */
    public QuestionStatus getStatus() {
        return status;
    }

    /**
     * Sets status.
     *
     * @param status the status
     */
    public void setStatus(QuestionStatus status) {
        this.status = status;
    }

    /**
     * Gets answer.
     *
     * @return the answer
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Sets answer.
     *
     * @param answer the answer
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Question)) {
            return false;
        }
        Question question = (Question) o;
        return id == question.id && upvotes == question.upvotes && score == question.score
            && type == question.type && Objects.equals(title, question.title)
            && Objects.equals(text, question.text)
            && Objects.equals(author, question.author)
            && Objects.equals(timeCreated, question.timeCreated) && status == question.status
            && Objects.equals(answer, question.answer);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(type, id, title, text, author, upvotes, score, timeCreated, status, answer);
    }

    @Override
    public String toString() {
        return "Question{"
            + "type=" + type
            + ", id=" + id
            + ", title='" + title + '\''
            + ", text='" + text + '\''
            + ", author=" + author
            + ", upvotes=" + upvotes
            + ", score=" + score
            + ", timeCreated=" + timeCreated
            + ", status=" + status
            + ", answer='" + answer + '\''
            + '}';
    }

    /**
     * The enum Type.
     */
    protected enum Type {
        /**
         * Open type.
         */
        OPEN,
        /**
         * Mc type.
         */
        MC
    }

    /**
     * The enum Question status.
     */
    protected enum QuestionStatus {
        /**
         * Open question status.
         */
        OPEN,
        /**
         * Answered question status.
         */
        ANSWERED,
        /**
         * Spam question status.
         */
        SPAM
    }
}

