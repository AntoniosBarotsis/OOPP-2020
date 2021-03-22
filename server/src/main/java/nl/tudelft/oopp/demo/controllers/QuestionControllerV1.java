package nl.tudelft.oopp.demo.controllers;

import java.util.Date;
import javax.websocket.server.PathParam;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;


/**
 * Question Controller.
 */
@RestController("QuestionV1")
@RequestMapping("api/v2/questions")
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
     * Sets the text of the question to be the question in questionHelper.
     *
     * @param questionId the question id
     * @param questionHelper the questionHelper with the next text
     */
    @PutMapping(value = "setText")
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
    @GetMapping(value = "getAuthor")
    public User getAuthor(@PathParam("questionId") long questionId) {
        return questionService.getAuthor(questionId);
    }


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "upvote")
    public void upvote(@PathParam("questionId") long questionId) {
        questionService.upvote(questionId);
    }


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "downvote")
    public void downvote(@PathParam("questionId") long questionId) {
        questionService.downvote(questionId);
    }


    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    @GetMapping(value = "getUpvotes")
    public int getUpvotes(@PathParam("questionId") long questionId) {
        return questionService.getUpvotes(questionId);
    }


    /**
     * Gets the score.
     *
     * @param questionId the question id
     * @return the score value of question
     */
    @GetMapping(value = "getScore")
    public int getScore(@PathParam("questionId") long questionId) {
        return questionService.getScore(questionId);
    }


    /**
     * Sets the score of question with value score.
     *
     * @param questionId the question id
     * @param score the new score value of question
     */
    @PutMapping(value = "setScore")
    public void setScore(@PathParam("questionId") long questionId, @PathParam("score") int score) {
        questionService.setScore(questionId, score);
    }


    /**
     * Gets the id of Question with the number highest score. So if 0 is passed
     * the question id of the highest score question is returned.
     *
     * @param number the number of question this should return
     * @return question id of highest score Question
     */
    @GetMapping(value = "getHighest")
    public long getHighest(@PathParam("number") int number) {
        return questionService.getHighest(number);
    }


    /**
     * Gets the date.
     *
     * @param questionId the question id
     * @return the question date
     */
    @GetMapping(value = "getTime")
    public Date getDate(@PathParam("questionId") long questionId) {
        return questionService.getDate(questionId);
    }


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    @GetMapping(value = "getStatus")
    public Question.QuestionStatus getStatus(@PathParam("questionId") long questionId) {
        return questionService.getStatus(questionId);
    }


    /**
     *Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "setAnswered")
    public void setAnswered(@PathParam("questionId") long questionId) {
        questionService.setAnswered(questionId);
    }


    /**
     *Sets the value of status as ANSWERED unless score is greater than 5.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "studentSetAsAnswered")
    public void studentSetAnswered(@PathParam("questionId") long questionId) {
        questionService.userSetAnswered(questionId);
    }


    /**
     *Sets the value of status as ANSWERED unless score is greater than maxScore.
     *
     * @param maxScore the max score for checking weather to mark as answered
     * @param questionId the question id
     */
    @PutMapping(value = "studentSetAnswered")
    public void studentSetAnswered(
            @PathParam("questionId") long questionId, @PathParam("maxScore") int maxScore) {
        questionService.userSetAnswered(questionId, maxScore);
    }


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "setSpam")
    public void setSpam(@PathParam("questionId") long questionId) {
        questionService.setSpam(questionId);
    }


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "setOpen")
    public void setOpen(@PathParam("questionId") long questionId) {
        questionService.setOpen(questionId);
    }


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @GetMapping(value = "getAnswer")
    public String getAnswer(@PathParam("questionId") long questionId) {
        return questionService.getAnswer(questionId);
    }


    /**
     * Sets the answer of question as the text of questionHelper.
     *
     * @param questionId the question id
     * @param questionHelper the questionHelper with the new answer as its text
     */
    @PutMapping(value = "setAnswer")
    public void setAnswer(@RequestBody QuestionHelper questionHelper,
                          @PathParam("questionId") long questionId) {
        questionService.setAnswer(questionId, questionHelper);
    }


}
