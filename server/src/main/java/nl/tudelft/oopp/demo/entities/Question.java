package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.core.JsonProcessingException;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.serializers.QuestionSerializer;
import nl.tudelft.oopp.demo.entities.users.User;
import org.hibernate.annotations.GenericGenerator;

/**
 * The Question class. This is used to represent questions users ask during lectures.
 */
@Entity(name = "Question")
@Table(name = "questions")
@Data
@NoArgsConstructor
@JsonSerialize(using = QuestionSerializer.class)
public class Question {
    @Id
    @GeneratedValue(
        generator = "question_sequence"
    )
    @GenericGenerator(
        strategy = "nl.tudelft.oopp.demo.entities.RandomIdGenerator",
        name = "question_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "text", columnDefinition = "TEXT")
    private String text;
    @ManyToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "author_id")
    private User author;
    @Column(name = "upvotes")
    private int upvotes;
    @Column(name = "score")
    private int score;
    @Column(name = "timeCreated")
    @EqualsAndHashCode.Exclude
    private Date timeCreated;
    @Column(name = "status")
    private QuestionStatus status;
    @Column(name = "answer")
    private String answer;

    /**
     * Instantiates a new Question.
     *
     * @param text   the text
     * @param author the author
     */
    public Question(String text, User author) {
        this.text = text;
        this.author = author;

        this.upvotes = 0;
        this.score = 0;
        this.status = QuestionStatus.OPEN;
        this.timeCreated = new Date();
    }

    /**
     * Export question to txt format.
     *
     * @return the string
     */
    public String exportToTxt() {
        return "TO BE IMPLEMENTED";
    }

    /**
     * Export question to json format.
     *
     * @return the string
     * @throws JsonProcessingException the json processing exception
     */
    public String exportToJson() throws JsonProcessingException {
        ObjectMapper objMapper = new ObjectMapper();

        return objMapper.writeValueAsString(this);
    }

    /**
     * Returns true if the question has been answered.
     *
     * @return the boolean
     */
    public boolean isAnswered() {
        return this.answer != null;
    }

    /**
     * The enum Question status.
     */
    public enum QuestionStatus {
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

    /**
     * Status to String mapping.
     *
     * @return the int
     */
    public String statusToString() {
        if (status == Question.QuestionStatus.OPEN) {
            return "OPEN";
        } else if (status == Question.QuestionStatus.ANSWERED) {
            return "ANSWERED";
        } else {
            return "SPAM";
        }
    }
}

