package nl.tudelft.oopp.demo.controllers.question;

import java.util.Date;

import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.services.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;


/**
 * Question Controller.
 */
@Controller
@RequestMapping("api/v1/questions")
public class QuestionController {
    @Autowired
    private final QuestionService questionService;

    /**
     * Instantiates a new Question controller.
     *
     * @param questionService the question service
     */
    public QuestionController(QuestionService questionService) {
        this.questionService = questionService;
    }

    /**
     * Gets the text of the question.
     *
     * @param questionId the question id
     * @return a String of the text of the question
     */
    @ResponseBody
    @GetMapping("getQuestion/{questionId}")
    public String getQuestion(@PathVariable long questionId) {
        return questionService.getQuestion(questionId);
    }


    /**
     * Sets the text of the question to be newQuestion.
     *
     * @param questionId the question id
     * @param newQuestion the value of text that will be set as question's text
     */
    @ResponseBody
    @PutMapping("setQuestion/{questionId}/{newQuestion}")
    public void editQuestion(@PathVariable long questionId, @PathVariable String newQuestion) {
        questionService.editQuestion(questionId, newQuestion);
    }


    /**
     * Gets the author.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    @ResponseBody
    @GetMapping("getAuthor/{questionId}")
    public User getAuthor(@PathVariable long questionId) {
        return questionService.getAuthor(questionId);
    }


    /**
     * Increases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @ResponseBody
    @PutMapping("upvote/{questionId}")
    public void incrementUpvotes(@PathVariable long questionId) {
        questionService.incrementUpvotes(questionId);
    }


    /**
     * Decreases the value of upvote by 1.
     *
     * @param questionId the question id
     */
    @ResponseBody
    @PutMapping("downvote/{questionId}")
    public void downvote(@PathVariable long questionId) {
        questionService.downvote(questionId);
    }


    /**
     * Gets the number of upvotes.
     *
     * @param questionId the question id
     * @return the value of upvotes
     */
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    @PutMapping("setScore/{questionId}/{score}")
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
    @ResponseBody
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
    @ResponseBody
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
    @ResponseBody
    @GetMapping("getStatus/{questionId}")
    public Enum getStatus(@PathVariable long questionId) {
        return questionService.getStatus(questionId);
    }


    /**
     *Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @ResponseBody
    @PutMapping("setAnswered/{questionId}")
    public void setAnswered(@PathVariable long questionId) {
        questionService.setAnswered(questionId);
    }


    /**
     * Sets the value of status as SPAM.
     *
     * @param questionId the question id
     */
    @ResponseBody
    @PutMapping("setSpam/{questionId}")
    public void setSpam(@PathVariable long questionId) {
        questionService.setSpam(questionId);
    }


    /**
     * Sets the value of status as OPEN.
     *
     * @param questionId the question id
     */
    @ResponseBody
    @PutMapping("setOpen/{questionId}")
    public void setOpen(@PathVariable long questionId) {
        questionService.setOpen(questionId);
    }


    /**
     * Gets the answer of the question.
     *
     * @param questionId the question id
     * @return the answer
     */
    @ResponseBody
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
    @ResponseBody
    @PutMapping("setAnswer/{questionId}/{answer}")
    public void setAnswer(@PathVariable long questionId, @PathVariable String answer) {
        questionService.setAnswer(questionId, answer);
    }


    /**
     * Gets the title of question.
     *
     * @param questionId the question id
     * @return the title of question
     */
    @ResponseBody
    @GetMapping("getTitle/{questionId}")
    public String getTitle(@PathVariable long questionId) {
        return questionService.getTitle(questionId);
    }


    /**
     * Sets the title of question as title.
     *
     * @param questionId the question id
     * @param title the new title of question
     */
    @ResponseBody
    @PutMapping("setTitle/{questionId}/{title}")
    public void setTitle(@PathVariable long questionId, @PathVariable String title) {
        questionService.setTitle(questionId, title);
    }

}
