package nl.tudelft.oopp.demo.entities.questions;

import java.util.Date;
import java.util.List;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.Table;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Mc question.
 */
@Entity
@Table(name = "mcQuestions")
public class McQuestion extends Question {
    @ElementCollection
    @Column(name = "options")
    private List<String> options;
    @ElementCollection
    @Column(name = "correct_answer")
    private List<String> correctAnswer;

    /**
     * Instantiates a new Mc question.
     *
     * @param title         the title
     * @param text          the text
     * @param author        the author
     * @param timeCreated   the time created
     * @param options       the options
     * @param correctAnswer the correct answer
     */
    public McQuestion(String title, String text, User author, Date timeCreated,
                      List<String> options, List<String> correctAnswer) {
        super(title, text, author, timeCreated);
        this.options = options;
        this.correctAnswer = correctAnswer;

        this.type = Type.MC;
    }

    /**
     * Instantiates a new Mc question.
     */
    public McQuestion() {

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
     * Gets correct answers.
     *
     * @return the correct answers
     */
    public List<String> getCorrectAnswer() {
        return correctAnswer;
    }

    /**
     * Sets correct answer index.
     *
     * @param correctAnswer the correct answer index
     */
    public void setCorrectAnswer(List<String> correctAnswer) {
        this.correctAnswer = correctAnswer;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof McQuestion)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        McQuestion that = (McQuestion) o;
        return correctAnswer.equals(that.correctAnswer)
            && Objects.equals(options, that.options);
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), options, correctAnswer);
    }

    @Override
    public String toString() {
        return "McQuestion{"
            + "options=" + options
            + ", correctAnswer=" + correctAnswer
            + '}';
    }
}