package nl.tudelft.oopp.demo.entities.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;

/**
 * The Question helper class. Used for deserializing in the controllers. Only has the Question
 * constructor fields as attributes.
 */
@Data
@NoArgsConstructor
public class QuestionHelper {
    private String text;
    private StudentHelper author;

    public QuestionHelper(String text, StudentHelper author) {
        this.text = text;
        this.author = author;
    }    /**
     * Create question.
     *
     * @return the question
     */
    public Question createQuestion() {
        return new Question(text, author.createStudent());
    }
}