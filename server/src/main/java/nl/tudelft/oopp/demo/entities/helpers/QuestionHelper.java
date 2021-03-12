package nl.tudelft.oopp.demo.entities.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.User;

/**
 * The type Question helper.
 */
@Data
@NoArgsConstructor
public class QuestionHelper {
    private String title;
    private String text;
    private User author;

    /**
     * Instantiates a new Question helper.
     *
     * @param title  the title
     * @param text   the text
     * @param author the author
     */
    public QuestionHelper(String title, String text, User author) {
        this.title = title;
        this.text = text;
        this.author = author;
    }

    /**
     * Create question question.
     *
     * @return the question
     */
    public Question createQuestion() {
        return new Question(title, text, author);
    }
}