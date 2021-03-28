package nl.tudelft.oopp.demo.communication.questionView;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;


import nl.tudelft.oopp.demo.communication.questionview.QuestionViewCommunication;
import nl.tudelft.oopp.demo.data.helper.QuestionHelper;
import nl.tudelft.oopp.demo.data.helper.StudentHelper;
import org.junit.jupiter.api.Test;



class QuestionViewCommunicationTest {
    private final String url = "http://localhost:8080/api/v2/questions/";

    @Test
    void sendEmptyPutRequest() {
        String link = url;
        assertDoesNotThrow(() -> QuestionViewCommunication.sendEmptyPutRequest(link));
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
        // assertDoesNotThrow(() -> QuestionViewCommunication.ban(1, 3));
    }

    @Test
    void delete() {
        // assertDoesNotThrow(() -> QuestionViewCommunication.delete(1, 1));
    }
}