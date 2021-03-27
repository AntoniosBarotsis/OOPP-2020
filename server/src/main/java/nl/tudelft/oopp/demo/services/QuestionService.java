package nl.tudelft.oopp.demo.services;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.module.SimpleModule;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;
import lombok.AllArgsConstructor;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.helpers.QuestionHelper;
import nl.tudelft.oopp.demo.entities.log.LogQuestion;
import nl.tudelft.oopp.demo.entities.serializers.QuestionSerializer;
import nl.tudelft.oopp.demo.entities.users.Student;
import nl.tudelft.oopp.demo.entities.users.User;
import nl.tudelft.oopp.demo.exceptions.InvalidIdException;
import nl.tudelft.oopp.demo.exceptions.UnauthorizedException;
import nl.tudelft.oopp.demo.repositories.LogEntryRepository;
import nl.tudelft.oopp.demo.repositories.QuestionRepository;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import nl.tudelft.oopp.demo.repositories.UserRepository;
import org.springframework.stereotype.Service;

/**
 * The type Question service.
 */
@Service
@AllArgsConstructor
public class QuestionService {
    private final QuestionRepository questionRepository;
    private final UserRepository userRepository;
    private final RoomRepository roomRepository;
    private final LogEntryRepository logEntryRepository;
    private final UserService userService;

    /**
     * Adds a question unless the user has been banned.
     *
     * @param question the question
     * @param roomId   the room id
     * @throws InvalidIdException    the invalid id exception
     * @throws UnauthorizedException the unauthorized exception
     */
    public void addQuestion(Question question, long roomId)
        throws InvalidIdException, UnauthorizedException {
        if (userService.isInvalidAuthorId(question.getAuthor())) {
            throw new InvalidIdException("The supplied author id is invalid");
        }

        if (userIsBanned(question, roomId)) {
            throw new UnauthorizedException("User not authorized "
                + "(you have been banned from this room)");
        }
        
        question.getAuthor().setId(question.getAuthor().getId());
        questionRepository.save(question);
        questionRepository.addQuestion(roomId, question.getId());

        Room room = roomRepository.getOne(roomId);

        LogQuestion logQuestion = new LogQuestion(room, (Student) question.getAuthor(),
            question, question.getTimeCreated());
        logEntryRepository.save(logQuestion);

        userRepository.addQuestionToUser(question.getAuthor().getId(), question.getId());
    }

    /**
     * Gets the first question entity with id questionId.
     *
     * @param questionId the question id
     * @return a question entity with id questionId.
     */
    public Question getQuestion(long questionId) {
        List<Long> listOfId = new ArrayList<>();
        listOfId.add(questionId);
        List<Question> listOfQuestion =  questionRepository.findAllById(listOfId);
        return listOfQuestion.get(0);
    }

    /**
     * Gets the text of the question.
     *
     * @param questionId the question id
     * @return a String of the text of the question
     */
    public String getText(long questionId) {
        return questionRepository.getText(questionId);
    }

    /**
     * Sets the text of the question.
     *
     * @param questionId  the question id
     * @param newQuestion the encoded value of text that will be set as question's text.
     */
    public void setText(long questionId, QuestionHelper newQuestion) {
        questionRepository.setText(questionId, newQuestion.getText());

    }

    /**
     * Sets the text of the question.
     *
     * @param questionId  the question id
     * @param newQuestion the encoded value of text that will be set as question's text.
     * @throws UnsupportedEncodingException the unsupported encoding exception
     */
    public void setText(long questionId, String newQuestion) throws UnsupportedEncodingException {
        questionRepository.setText(questionId, URLDecoder
                .decode(newQuestion, StandardCharsets.UTF_8.toString()));
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
    public void upvote(long questionId) {
        questionRepository.upvote(questionId);
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
     * @param score      the new score value of question
     */
    public void setScore(long questionId, int score) {
        questionRepository.setScore(questionId, Math.max(score, 0));
    }


    /**
     * Gets the id of Question with the number highest score. So if 0 is passed
     * the question id of the highest score question is returned.
     *
     * @param number the number of question this should return
     * @return question id of highest score Question
     */
    public long getHighest(int number) {
        return questionRepository.getHighestScore().get(number);
    }

    /**
     * Gets the date.
     *
     * @param questionId the question id
     * @return the question date
     */
    public Date getDate(long questionId) {
        return questionRepository.getDate(questionId);
    }


    /**
     * Gets the status of question.
     *
     * @param questionId the question id
     * @return the status of question
     */
    public Question.QuestionStatus getStatus(long questionId) {
        return questionRepository.getStatus(questionId);
    }


    /**
     * Sets the value of status as ANSWERED.
     *
     * @param questionId the question id
     */
    public void setAnswered(long questionId) {
        questionRepository.setAnswered(questionId);
    }

    /**
     * Sets the value of status as ANSWERED, unless its score is greater than 5.
     *
     * @param questionId the question id
     */
    public void userSetAnswered(long questionId) {
        if (questionRepository.getScore(questionId) <= 5) {
            questionRepository.setAnswered(questionId);
        }
    }

    /**
     * Sets the value of status as ANSWERED, unless its score is greater than maxScore.
     *
     * @param questionId the question id
     * @param maxScore   the max score for changing the status to answered
     */
    public void userSetAnswered(long questionId, int maxScore) {
        if (questionRepository.getScore(questionId) <= maxScore) {
            questionRepository.setAnswered(questionId);
        }
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
     * Sets the answer of question as the text of questionHelper.
     *
     * @param questionId     the question id
     * @param questionHelper the questionHelper with the new answer as its text
     */
    public void setAnswer(long questionId, QuestionHelper questionHelper) {
        questionRepository.setAnswer(questionId, questionHelper.getText());
    }


    /**
     * Sets the answer of question as answer.
     *
     * @param questionId the question id
     * @param answer     the new answer of question
     */
    public void setAnswer(long questionId, String answer) {
        questionRepository.setAnswer(questionId, answer);
    }


    /**
     * Delete one question.
     *
     * @param roomId     the room id
     * @param questionId the question id
     */
    public void deleteOneQuestion(long roomId, long questionId) {
        questionRepository.deleteOneQuestion(roomId, questionId);
    }

    /**
     * Delete all questions.
     *
     * @param roomId the room id
     */
    public void deleteAllQuestions(long roomId) {
        questionRepository.deleteAllQuestions(roomId);
    }

    /**
     * Exports a single question in JSON format.
     *
     * @param questionId the question id
     * @return the string
     */
    public Question export(long questionId) {
        return questionRepository.findById(questionId).get();
    }

    /**
     * Exports all questions from a given room in JSON format.
     *
     * @param roomId the room id
     * @return the string
     */
    public Set<Question> exportAll(long roomId) {
        return roomRepository.findAllQuestions(roomId);
    }

    /**
     * Exports a given amount of questions in JSON format sorted by score.
     *
     * @param roomId - the room id
     * @param amount - the amount of questions
     * @return the string
     */
    public List<Question> exportTop(long roomId, int amount) {
        if (amount < 1) {
            throw new IllegalArgumentException("Invalid amount supplied");
        }

        return roomRepository
            .findAllQuestions(roomId)
            .stream()
            .sorted(Comparator.comparingInt(Question::getUpvotes).reversed())
            .limit(amount)
            .collect(Collectors.toList());
    }

    /**
     * Export answered questions string.
     *
     * @param roomId the room id
     * @return the string
     */
    public List<Question> exportAnswered(long roomId) {
        return roomRepository
            .findAllQuestions(roomId)
            .stream()
            .filter(Question::isAnswered)
            .collect(Collectors.toList());
    }

    /**
     * Returns true if the user is banned in the given room.
     * @param question the question
     * @param roomId the room id
     * @return boolean
     */
    private boolean userIsBanned(Question question, long roomId) {
        return roomRepository.getOne(roomId)
            .getBannedIps()
            .contains(question.getAuthor().getIp());
    }
}
