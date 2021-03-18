package nl.tudelft.oopp.demo.data.helper;

public class QuestionHelper {
    private String text;
    private StudentHelper author;

    /**
     * Initialises a helper question used for sending json data.
     * @param text text of question
     * @param author author of question
     */
    public QuestionHelper(String text, StudentHelper author) {
        this.text = text;
        this.author = author;
    }
}