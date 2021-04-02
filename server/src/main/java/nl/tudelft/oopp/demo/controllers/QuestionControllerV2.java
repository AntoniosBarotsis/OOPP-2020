package nl.tudelft.oopp.demo.controllers;

import java.util.Date;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;

import lombok.AllArgsConstructor;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.helpers.QuestionExportHelper;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidIdException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.services.QuestionService;
import nl.tudelft.oopp.demo.services.UserService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


/**
 * The type Question controller.
 */
@RestController("QuestionV2")
@RequestMapping("api/v2/questions")
@AllArgsConstructor
public class QuestionControllerV2 {
    private final QuestionService questionService;
    private final UserService userService;

    /**
     * Add question to the given room.
     *
     * @param questionHelper the question helper
     * @param roomId         the room id
     * @param authorId       the author id
     * @param request        the request
     * @throws InvalidIdException    the invalid id exception
     * @throws UnauthorizedException the unauthorized exception
     */
    @PostMapping("add")
    public void addQuestion(@RequestBody QuestionHelper questionHelper,
                            @PathParam("roomId") long roomId,
                            @PathParam("authorId") long authorId,
                            HttpServletRequest request)
        throws InvalidIdException, UnauthorizedException {

        Question question = questionHelper.createQuestion();
        question.getAuthor().setIp(request.getRemoteAddr());
        question.getAuthor().setId(authorId);

        questionService.addQuestion(question, roomId);
    }

    /**
     * Delete a question using the id.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    @DeleteMapping("deleteOne")
    public void deleteOneQuestion(@PathParam("roomId") long roomId,
                                  @PathParam("questionId") long questionId) {
        questionService.deleteOneQuestion(roomId, questionId);
    }

    /**
     * Delete all questions in a room.
     *
     * @param roomId the room id
     */
    @DeleteMapping("deleteAll")
    public void deleteAllQuestions(@PathParam("roomId") long roomId) {
        questionService.deleteAllQuestions(roomId);
    }

    /**
     * Export a specific question to JSON.
     *
     * @param questionId the question id
     * @return the question
     */
    @GetMapping("export")
    public List<QuestionExportHelper> export(@PathParam("questionId") long questionId) {
        return questionService.export(questionId);
    }

    /**
     * Export all questions from a room to JSON.
     *
     * @param roomId the room id
     * @return the set
     */
    @GetMapping("exportAll")
    public List<QuestionExportHelper> exportAll(@PathParam("roomId") long roomId) {
        return questionService.exportAll(roomId);
    }


    /**
     * Export a variable amount of questions from a room to JSON.
     *
     * @param roomId the room id
     * @param amount the amount
     * @return the list
     */
    @GetMapping("exportTop")
    public List<QuestionExportHelper> exportTop(@PathParam("roomId") long roomId,
                                                @PathParam("amount") int amount) {
        return questionService.exportTop(roomId, amount);
    }

    /**
     * Export all answered questions from a room to JSON.
     *
     * @param roomId the room id
     * @return the list
     */
    @GetMapping("exportAnswered")
    public List<QuestionExportHelper> exportAnswered(@PathParam("roomId") long roomId) {
        return questionService.exportAnswered(roomId);
    }

    /**
     * Sort questions by score.
     *
     * @param roomId the room id
     * @return the list
     */
    @GetMapping("sortByScore")
    public List<Question> sortByScore(@PathParam("roomId") long roomId) {
        return questionService.sortByScore(roomId);
    }

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
     * Sets the text of the question given a questionHelper object.
     *
     * @param questionHelper the questionHelper with the next text
     * @param questionId     the question id
     */
    @PutMapping(value = "setText")
    public void setText(@RequestBody QuestionHelper questionHelper,
                        @PathParam("questionId") long questionId) {
        questionService.setText(questionId, questionHelper);
    }


    /**
     * Gets the author of a question.
     *
     * @param questionId the question id
     * @return the author of the question
     */
    @GetMapping(value = "getAuthor")
    public User getAuthor(@PathParam("questionId") long questionId) {
        return questionService.getAuthor(questionId);
    }


    /**
     * Upvotes a question.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "upvote")
    public void upvote(@PathParam("questionId") long questionId) {
        questionService.upvote(questionId);
    }


    /**
     * Downvotes a question.
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
     * @return the score
     */
    @GetMapping(value = "getScore")
    public int getScore(@PathParam("questionId") long questionId) {
        return questionService.getScore(questionId);
    }


    /**
     * Sets the score of question.
     *
     * @param questionId the question id
     * @param score      the new score value of question
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
     * Gets the date a question was asked.
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
     * @return the status
     */
    @GetMapping(value = "getStatus")
    public Question.QuestionStatus getStatus(@PathParam("questionId") long questionId) {
        return questionService.getStatus(questionId);
    }


    /**
     * Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "setAnswered")
    public void setAnswered(@PathParam("questionId") long questionId) {
        questionService.setAnswered(questionId);
    }


    /**
     * Sets the value of status as ANSWERED unless score is greater than 5.
     *
     * @param questionId the question id
     */
    @PutMapping(value = "studentSetAsAnswered")
    public void studentSetAnswered(@PathParam("questionId") long questionId) {
        questionService.userSetAnswered(questionId);
    }


    /**
     * Sets the value of status as ANSWERED unless score is greater than maxScore.
     *
     * @param questionId the question id
     * @param maxScore   the max score for checking weather to mark as answered
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
     * @param questionHelper the questionHelper with the new answer as its text
     * @param questionId     the question id
     */
    @PutMapping(value = "setAnswer")
    public void setAnswer(@RequestBody QuestionHelper questionHelper,
                          @PathParam("questionId") long questionId) {
        questionService.setAnswer(questionId, questionHelper);
    }

    /**
     * Sets the BeingAnswered field of a question to the associated boolean value.
     *
     * @param questionId the id of the corresponding question
     * @param status     the boolean value of the field
     */
    @PutMapping(value = "setBeingAnswered")
    public void setBeingAnswered(@PathParam("questionId") long questionId,
                                 @PathParam("status") boolean status) {
        questionService.setBeingAnswered(questionId, status);
    }

    /**
     * Retireves the beingAnswered field of a question.
     *
     * @param questionId the id of the corresponding question
     * @return the being answered
     */
    @GetMapping(value = "getBeingAnswered")
    public boolean getBeingAnswered(@PathParam("questionId") long questionId) {
        return questionService.getBeingAnswered(questionId);
    }
}
