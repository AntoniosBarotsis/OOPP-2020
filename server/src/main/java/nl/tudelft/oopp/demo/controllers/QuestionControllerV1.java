package nl.tudelft.oopp.demo.controllers;

import java.io.UnsupportedEncodingException;
import java.util.Date;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.websocket.server.PathParam;


/**
 * Question Controller.
 */
@RestController("QuestionV1")
@RequestMapping("api/v1/questions")
@AllArgsConstructor
public class QuestionControllerV1 {

    private final QuestionService questionService;



    /**
     * Gets the entity question with id questionId.
     *
     * @param questionId the question id
     * @return Question entity with id questionId
     */

    @GetMapping(value = "getQuestion")
    public Question getQuestion(@PathParam("questionId") long questionId) {
        return questionService.getQuestion(questionId);
    }


    /**
     * Gets the text of the question.
     *
     * @param questionId the question id
     * @return a String of the text of the question
     */
    @GetMapping(value = "getText")
    public String getText(@PathParam("questionId") long questionId) {
        return questionService.getText(questionId);
    }

    /**
     * Sets the text of the question to be decoded newQuestion.
     *
     * @param questionId the question id
     * @param newQuestion the encoded value of text that will be set as question's text
     */
    @PostMapping("setText")
    public void setText(@RequestBody QuestionHelper questionHelper,
                        @PathParam("questionId") long questionId) {
        questionService.setText(questionId, questionHelper);
    }


    /**
     * Gets the author.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    @GetMapping("getAuthor/{questionId}")
    public User getAuthor(@PathVariable long questionId) {
        return questionService.getAuthor(questionId);
    }


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @GetMapping("upvote/{questionId}")
    public void upvote(@PathVariable long questionId) {
        questionService.upvote(questionId);
    }


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @GetMapping("downvote/{questionId}")
    public void downvote(@PathVariable long questionId) {
        questionService.downvote(questionId);
    }


    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    @GetMapping("getUpvotes/{questionId}")
    public int getUpvotes(@PathVariable long questionId) {
        return questionService.getUpvotes(questionId);
    }


    /**
     * Gets the score.
     *
     * @param questionId the question id
     * @return the score value of question
     */
    @GetMapping("getScore/{questionId}")
    public int getScore(@PathVariable long questionId) {
        return questionService.getScore(questionId);
    }


    /**
     * Sets the score of question with value score.
     *
     * @param questionId the question id
     * @param score the new score value of question
     */
    @GetMapping("setScore/{questionId}/{score}")
    public void setScore(@PathVariable long questionId, @PathVariable int score) {
        questionService.setScore(questionId, score);
    }


    /**
     * Gets the id of Question with the number highest score. So if 0 is passed
     * the question id of the highest score question is returned.
     *
     * @param number the number of question this should return
     * @return question id of highest score Question
     */
    @GetMapping("get/{number}")
    public long get(@PathVariable int number) {
        return questionService.get(number);
    }


    /**
     * Gets the date.
     *
     * @param questionId the question id
     * @return the question date
     */
    @GetMapping("getTime/{questionId}")
    public Date getTime(@PathVariable long questionId) {
        return questionService.getTime(questionId);
    }


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    @GetMapping("getStatus/{questionId}")
    public Question.QuestionStatus getStatus(@PathVariable long questionId) {
        return questionService.getStatus(questionId);
    }


    /**
     *Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @GetMapping("setAnswered/{questionId}")
    public void setAnswered(@PathVariable long questionId) {
        questionService.setAnswered(questionId);
    }


    /**
     *Sets the value of status as ANSWERED unless score is greater than 5.
     *
     * @param questionId the question id
     */
    @GetMapping("studentSetAnswered/{questionId}")
    public void userSetAnswered(@PathVariable long questionId) {
        questionService.userSetAnswered(questionId);
    }


    /**
     *Sets the value of status as ANSWERED unless score is greater than maxScore.
     *
     * @param maxScore the max score for checking weather to mark as answered
     * @param questionId the question id
     */
    @GetMapping("studentSetAnswered/{questionId}/{maxScore}")
    public void userSetAnswered(@PathVariable long questionId, @PathVariable int maxScore) {
        questionService.userSetAnswered(questionId, maxScore);
    }


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    @GetMapping("setSpam/{questionId}")
    public void setSpam(@PathVariable long questionId) {
        questionService.setSpam(questionId);
    }


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    @GetMapping("setOpen/{questionId}")
    public void setOpen(@PathVariable long questionId) {
        questionService.setOpen(questionId);
    }


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @GetMapping("getAnswer/{questionId}")
    public String getAnswer(@PathVariable long questionId) {
        return questionService.getAnswer(questionId);
    }


    /**
     * Sets the answer of question as answer.
     *
     * @param questionId the question id
     * @param answer the new answer of question
     */
    @GetMapping("setAnswer/{questionId}/{answer}")
    public void setAnswer(@PathVariable long questionId, @PathVariable String answer) throws UnsupportedEncodingException {
        questionService.setAnswer(questionId, answer);
    }

}
