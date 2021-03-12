package nl.tudelft.oopp.demo.entities;

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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The Question class.
 */
@Entity(name = "Question")
@Table(name = "questions")
@Data
@NoArgsConstructor
public class Question {
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
     * @param title  the title
     * @param text   the text
     * @param author the author
     */
    public Question(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;

        this.upvotes = 0;
        this.score = 0;
        this.status = QuestionStatus.OPEN;
        this.timeCreated = new Date();
        this.answer = "";
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
}

