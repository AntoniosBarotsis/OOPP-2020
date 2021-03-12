package nl.tudelft.oopp.demo.entities.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;

/**
 * The Question helper class.
 */
@Data
@NoArgsConstructor
public class QuestionHelper {
    private String title;
    private String text;
    private StudentHelper author;

    /**
     * Create question.
     *
     * @return the question
     */
    public Question createQuestion() {
        return new Question(title, text, author.createStudent());
    }
}