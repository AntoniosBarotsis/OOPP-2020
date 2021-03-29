package nl.tudelft.oopp.demo.communication.questionview;

import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.QuestionAuthor;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertNotNull;

class QuestionViewCommunicationTest {
    private final String url = "http://localhost:8080/api/v2/questions/";
    private Question q1;
    private long id;
    private long userId;

    @BeforeEach
    void loadData() {
        q1 = new Question(1, "Question", new QuestionAuthor(2, "Author"), 0, 0,
                new Date(), Question.QuestionStatus.OPEN, "", false);
        id = q1.getId();
        userId = q1.getAuthor().getId();
    }

    @Test
    void sendEmptyPutRequest() {
        assertDoesNotThrow(() -> QuestionViewCommunication.sendEmptyPutRequest(url));
    }

    @Test
    void upvote() {
        assertDoesNotThrow(() -> QuestionViewCommunication.upvote(id));
    }

    @Test
    void studentMarkAsAnswer() {
        assertDoesNotThrow(() -> QuestionViewCommunication.studentMarkAsAnswer(id));
    }

    @Test
    void modMarkAsAnswer() {
        assertDoesNotThrow(() -> QuestionViewCommunication.modMarkAsAnswer(id));
    }

    @Test
    void banUser() {
        assertDoesNotThrow(() -> QuestionViewCommunication.banUser(id));
    }

    @Test
    void downvote() {
        assertDoesNotThrow(() -> QuestionViewCommunication.downvote(id));
    }

    @Test
    void setText() {
        StudentHelper user = new StudentHelper("Student 1", "IP address");
        QuestionHelper qhelp = new QuestionHelper("This is a question", user);
        assertDoesNotThrow(() -> QuestionViewCommunication.setText(id, qhelp));
    }

    @Test
    void setAnswer() {
        StudentHelper user = new StudentHelper("Student 1", "IP address");
        QuestionHelper qhelp = new QuestionHelper("This is an answer", user);
        assertDoesNotThrow(() -> QuestionViewCommunication.setAnswer(id, qhelp));
    }

    @Test
    void addQuestionUpvoted() {
        assertDoesNotThrow(() -> QuestionViewCommunication.addQuestionUpvoted(id, userId));
    }

    @Test
    void removeQuestionUpvoted() {
        assertDoesNotThrow(() -> QuestionViewCommunication.removeQuestionUpvoted(id, userId));
    }

    @Test
    void ban() {
         // assertThrows(QuestionViewCommunication.ban(id, userId));
    }

    @Test
    void delete() {
        // assertDoesNotThrow(() -> QuestionViewCommunication.delete(id, userId));
    }

    @Test
    void setBeingAnswered() {
        assertDoesNotThrow(() -> QuestionViewCommunication.setBeingAnswered(id, true));
    }

    @Test
    void getBeingAnswered() {
        assertNotNull(QuestionViewCommunication.getBeingAnswered(id));
    }
}