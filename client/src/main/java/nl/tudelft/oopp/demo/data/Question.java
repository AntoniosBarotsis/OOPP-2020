package nl.tudelft.oopp.demo.data;

import com.google.gson.annotations.JsonAdapter;
import nl.tudelft.oopp.demo.data.deserializers.QuestionInstanceCreator;

import java.util.Date;

@JsonAdapter(QuestionInstanceCreator.class)
public class Question {
    private long id;
    private String text;
    private QuestionAuthor author;
    private int upvotes;
    private int score;
    private Date timeCreated;
    private QuestionStatus status;
    private String answer;
    private boolean isBeingAnswered;


    /**
     * Initializes a new Question.
     * @param id id of question
     * @param text text of question
     * @param author author of question
     * @param upvotes upvotes of question
     * @param score score of question
     * @param timeCreated time the question was created
     * @param status status of question
     * @param answer answer of question
     */
    public Question(long id, String text, QuestionAuthor author, int upvotes, int score,
                    Date timeCreated, QuestionStatus status,
                    String answer, boolean isBeingAnswered) {
        this.id = id;
        this.text = text;
        this.author = author;
        this.upvotes = upvotes;
        this.score = score;
        this.timeCreated = timeCreated;
        this.status = status;
        this.answer = answer;
        this.isBeingAnswered = isBeingAnswered;
    }

    /**
     * Getter for id of question.
     * @return id of user
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for id of question.
     * @param id id of user
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for text of question.
     * @return text of question
     */
    public String getText() {
        return text;
    }

    /**
     * Setter for text of question.
     * @param text text of question
     */
    public void setText(String text) {
        this.text = text;
    }

    /**
     * Getter for author of question.
     * @return author of question
     */
    public QuestionAuthor getAuthor() {
        return author;
    }

    /**
     * Setter for author of question.
     * @param author author of question
     */
    public void setAuthor(QuestionAuthor author) {
        this.author = author;
    }

    /**
     * Getter for upvotes of question.
     * @return upvotes of question
     */
    public int getUpvotes() {
        return upvotes;
    }

    /**
     * Setter for upvotes of question.
     * @param upvotes upvotes of question
     */
    public void setUpvotes(int upvotes) {
        this.upvotes = upvotes;
    }

    /**
     * Getter for score of question.
     * @return score of question
     */
    public int getScore() {
        return score;
    }

    /**
     * Setter for score of question.
     * @param score score of question
     */
    public void setScore(int score) {
        this.score = score;
    }

    /**
     * Getter for creation time of question.
     * @return creation time of question
     */
    public Date getTimeCreated() {
        return timeCreated;
    }

    /**
     * Setter for creation time of question.
     * @param timeCreated creation time of question
     */
    public void setTimeCreated(Date timeCreated) {
        this.timeCreated = timeCreated;
    }

    /**
     * Getter for status of question.
     * @return status of question
     */
    public QuestionStatus getStatus() {
        return status;
    }

    /**
     * Setter for status of question.
     * @param status status of question
     */
    public void setStatus(QuestionStatus status) {
        this.status = status;
    }

    /**
     * Getter for answer of question.
     * @return answer of question
     */
    public String getAnswer() {
        return answer;
    }

    /**
     * Setter for answer of question.
     * @param answer answer of question
     */
    public void setAnswer(String answer) {
        this.answer = answer;
    }

    /**
     * Getter for the beingAnswered field.
     * @return the state of the question
     */
    public boolean getIsBeingAnswered() {
        return isBeingAnswered;
    }

    /**
     * Setter for the boolean beingAnswered.
     * @param beingAnswered whether a question is being answered or not
     */
    public void setBeingAnswered(boolean beingAnswered) {
        isBeingAnswered = beingAnswered;
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
