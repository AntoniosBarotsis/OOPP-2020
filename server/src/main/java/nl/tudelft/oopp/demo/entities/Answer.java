package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

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

@Entity(name = "Answer")
@Table(name = "answers")
@Data
@NoArgsConstructor
public class Answer {

    @Id
    @SequenceGenerator(
            name = "answer_sequence",
            sequenceName = "answer_sequence",
            allocationSize = 1
    )
    @GeneratedValue(
            strategy = SEQUENCE,
            generator = "answer_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;

    @ElementCollection
    @Column(name = "answers")
    private List<String> answers;

    @Column(name = "poll_id")
    private long pollId;

    @Column(name = "user_id")
    private long userId;

    /**
     * Instantiates a new Answer.
     *
     * @param answers the answers
     * @param pollId  the poll's id
     * @param userId  the user's id
     */
    public Answer(List<String> answers, long pollId, long userId) {
        this.answers = answers;
        this.pollId = pollId;
        this.userId = userId;
    }
}
