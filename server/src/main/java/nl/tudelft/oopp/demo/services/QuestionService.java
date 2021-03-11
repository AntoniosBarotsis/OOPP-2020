package nl.tudelft.oopp.demo.services;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;




/**
 * Question service.
 */
@Service
public class QuestionService {

    @Autowired
    private final QuestionRepository questionRepository;


    /**
     * Instantiates a new Question repository.
     *
     * @param questionRepository the question repository
     */
    public QuestionService(QuestionRepository questionRepository) {
        this.questionRepository = questionRepository;
    }


    /**
     * Gets the text of the question.
     *
     * @param questionId the question id
     * @return a String of the text of the question
     */
    public String getQuestion(long questionId) {
        return questionRepository.getQuestion(questionId);
    }


    /**
     * Sets the text of the question to be newQuestion.
     *
     * @param questionId the question id
     * @param newQuestion the value of text that will be set as question's text
     */
    public void editQuestion(long questionId, String newQuestion) {
        questionRepository.editQuestion(questionId, newQuestion);
    }


    /**
     * Gets the author.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    public User getAuthor(long questionId) {
        return questionRepository.getAuthor(questionId);
    }


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    public void incrementUpvotes(long questionId) {
        questionRepository.incrementUpvotes(questionId);
    }


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    public void downvote(long questionId) {
        if (questionRepository.getUpvotes(questionId) > 0) {
            questionRepository.downvote(questionId);
        }
    }


    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    public int getUpvotes(long questionId) {
        return questionRepository.getUpvotes(questionId);
    }


    /**
     * Gets the score.
     *
     * @param questionId the question id
     * @return the score value of question
     */
    public int getScore(long questionId) {
        return questionRepository.getScore(questionId);
    }


    /**
     * Sets the score of question with value score.
     *
     * @param questionId the question id
     * @param score the new score value of question
     */
    public void setScore(long questionId, int score) {
        if (score >= 0) {
            questionRepository.setScore(questionId, score);
        } else {
            questionRepository.setScore(questionId, 0);
        }
    }


    /**
     * Gets the id of Question with the number highest score. So if 0 is passed
     * the question id of the highest score question is returned.
     *
     * @param number the number of question this should return
     * @return question id of highest score Question
     */
    public long get(int number) {
        return questionRepository.getHighestScore().get(number);
    }


    /**
     * Gets the date.
     *
     * @param questionId the question id
     * @return the question date
     */
    public Date getTime(long questionId) {
        return questionRepository.getTime(questionId);
    }


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    public Enum getStatus(long questionId) {
        return questionRepository.getStatus(questionId);
    }


    /**
     *Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    public void setAnswered(long questionId) {
        questionRepository.setAnswered(questionId);
    }

    /**
     *Sets the value of status as ANSWERED, unless too popular.
     *
     * @param questionId the question id
     */
    public void userSetAnswered(long questionId) {
        if (questionRepository.getScore(questionId) <= 5)
        questionRepository.setAnswered(questionId);
    }


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    public void setSpam(long questionId) {
        questionRepository.setSpam(questionId);
    }


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    public void setOpen(long questionId) {
        questionRepository.setOpen(questionId);
    }


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    public String getAnswer(long questionId) {
        return  questionRepository.getAnswer(questionId);
    }


    /**
     * Sets the answer of question as answer.
     *
     * @param questionId the question id
     * @param answer the new answer of question
     */
    public void setAnswer(long questionId, String answer) {
        questionRepository.setAnswer(questionId, answer);
    }


    /**
     * Gets the title of question.
     *
     * @param questionId the question id
     * @return the title of question
     */
    public String getTitle(long questionId) {
        return questionRepository.getTitle(questionId);
    }


    /**
     * Sets the title of question as title.
     *
     * @param questionId the question id
     * @param title the new title of question
     */
    public void setTitle(long questionId, String title) {
        questionRepository.setTitle(questionId, title);
    }



}
