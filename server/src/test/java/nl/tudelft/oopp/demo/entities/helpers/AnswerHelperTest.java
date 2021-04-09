package nl.tudelft.oopp.demo.entities.helpers;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class AnswerHelperTest {

    AnswerHelper a1;
    Answer a2;
    AnswerHelper b1;
    AnswerHelper c1;
    AnswerHelper a3;

    List<String> choice1;
    List<String> choice2;
    List<String> choice3;

    @BeforeEach
    void setUp() {

        choice1 = new ArrayList<>();
        choice2 = new ArrayList<>();
        choice3 = new ArrayList<>();

        choice1.add("Answer 1");
        choice1.add("Answer 2");
        choice2.add("Answer 1");
        choice3.add("Answer 3");

        a1 = new AnswerHelper();
        a1.setAnswers(choice1);
        a1.setPollId(1);
        a1.setUserId(1);

        a2 = new Answer(choice1, 1, 1);

        b1 = new AnswerHelper();
        b1.setAnswers(choice2);
        b1.setPollId(1);
        b1.setUserId(2);

        c1 = new AnswerHelper();
        c1.setAnswers(choice3);
        c1.setPollId(2);
        c1.setUserId(3);

        a3 = new AnswerHelper();
        a3.setUserId(1);
        a3.setPollId(1);
        a3.setAnswers(choice1);
    }

    @Test
    void createAnswer() {
        assertThat(a1.createAnswer()).isEqualTo(a2);
    }

    @Test
    void getAnswers() {
        assertThat(a1.getAnswers()).isEqualTo(choice1);
        assertThat(b1.getAnswers()).isEqualTo(choice2);
        assertThat(c1.getAnswers()).isEqualTo(choice3);
    }

    @Test
    void getPollId() {
        assertThat(a1.getPollId()).isEqualTo(1);
        assertThat(b1.getPollId()).isEqualTo(1);
        assertThat(c1.getPollId()).isEqualTo(2);
    }

    @Test
    void getUserId() {
        assertThat(a1.getUserId()).isEqualTo(1);
        assertThat(b1.getUserId()).isEqualTo(2);
        assertThat(c1.getUserId()).isEqualTo(3);
    }

    @Test
    void setAnswers() {
        List<String> newAnswers = new ArrayList<>();
        newAnswers.add("Answer 4");
        a1.setAnswers(newAnswers);

        assertThat(a1.getAnswers()).isEqualTo(newAnswers);
    }

    @Test
    void setPollId() {
        int newId = 9;
        a1.setPollId(newId);

        assertThat(a1.getPollId()).isEqualTo(newId);
    }

    @Test
    void setUserId() {
        int newId = 9;
        a1.setUserId(newId);

        assertThat(a1.getUserId()).isEqualTo(newId);
    }

    @Test
    void testEquals() {
        assertThat(a1).isNotEqualTo(b1);
        assertThat(a1).isEqualTo(a3);
    }

    @Test
    void testHashCode() {
        int hashed = a1.hashCode();
        assertThat(hashed).isNotNull();
        assertThat(a1.hashCode()).isEqualTo(a3.hashCode());
        assertThat(a1.createAnswer().hashCode()).isEqualTo(a3.createAnswer().hashCode());
    }

    @Test
    void testToString() {
        String expected = "AnswerHelper(pollId=1, userId=1, answers=[Answer 1, Answer 2])";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}

