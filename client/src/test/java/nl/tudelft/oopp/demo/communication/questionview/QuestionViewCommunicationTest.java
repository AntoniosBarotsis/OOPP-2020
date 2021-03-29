package nl.tudelft.oopp.demo.communication.questionview;

import nl.tudelft.oopp.demo.data.Question;
import nl.tudelft.oopp.demo.data.QuestionAuthor;
import nl.tudelft.oopp.demo.data.Room;
import nl.tudelft.oopp.demo.data.RoomConfig;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;
import org.junit.jupiter.api.Test;

import java.util.Date;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class QuestionViewCommunicationTest {
    private final String url = "http://localhost:8080/api/v2/questions/";

    @Test
    void sendEmptyPutRequest() {
        assertDoesNotThrow(() -> QuestionViewCommunication.sendEmptyPutRequest(url));
    }

    @Test
    void upvote() {
        assertDoesNotThrow(() -> QuestionViewCommunication.upvote(1));
    }

    @Test
    void studentMarkAsAnswer() {
        assertDoesNotThrow(() -> QuestionViewCommunication.studentMarkAsAnswer(1));
    }

    @Test
    void modMarkAsAnswer() {
        assertDoesNotThrow(() -> QuestionViewCommunication.modMarkAsAnswer(1));
    }

    @Test
    void banUser() {
        assertDoesNotThrow(() -> QuestionViewCommunication.banUser(1));
    }

    @Test
    void downvote() {
        assertDoesNotThrow(() -> QuestionViewCommunication.downvote(1));
    }

    @Test
    void setText() {
        StudentHelper user = new StudentHelper("Student 1", "IP address");
        QuestionHelper qhelp = new QuestionHelper("This is a question", user);
        assertDoesNotThrow(() -> QuestionViewCommunication.setText(1, qhelp));
    }

    @Test
    void setAnswer() {
        StudentHelper user = new StudentHelper("Student 1", "IP address");
        QuestionHelper qhelp = new QuestionHelper("This is an answer", user);
        assertDoesNotThrow(() -> QuestionViewCommunication.setAnswer(1, qhelp));
    }

    @Test
    void addQuestionUpvoted() {
        assertDoesNotThrow(() -> QuestionViewCommunication.addQuestionUpvoted(1, 1));
    }

    @Test
    void removeQuestionUpvoted() {
        assertDoesNotThrow(() -> QuestionViewCommunication.removeQuestionUpvoted(1, 1));
    }

    @Test
    void ban() {
         //assertThrows(QuestionViewCommunication.ban(1, 1));
    }

    @Test
    void delete() {
        Room a = new Room(1, "Title", new Date(), true, 0, 0, 100, true,
                new RoomConfig(2, 2,2, 2));
        Question b = new Question(1, "Question", new QuestionAuthor(1, "Author"), 0, 0, new Date(),
                Question.QuestionStatus.OPEN, "", false);
        assertDoesNotThrow(() -> QuestionViewCommunication.delete(1, 1));
    }


    @Test
    void setBeingAnswered() {
        assertDoesNotThrow(() -> QuestionViewCommunication.setBeingAnswered(1, true));
    }
}