package nl.tudelft.oopp.demo.entities.helpers;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Answer;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class AnswerHelperTest {

    AnswerHelper a;
    Answer a2;
    AnswerHelper b;
    AnswerHelper c;
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

        a = new AnswerHelper();
        a.setAnswers(choice1);
        a.setPollId(1);
        a.setUserId(1);

        a2 = new Answer(choice1, 1, 1);

        b = new AnswerHelper();
        b.setAnswers(choice2);
        b.setPollId(1);
        b.setUserId(2);

        c = new AnswerHelper();
        c.setAnswers(choice3);
        c.setPollId(2);
        c.setUserId(3);

        a3 = new AnswerHelper();
        a3.setUserId(1);
        a3.setPollId(1);
        a3.setAnswers(choice1);
    }

    @Test
    void createAnswer() {
        assertThat(a.createAnswer()).isEqualTo(a2);
    }

    @Test
    void getAnswers() {
        assertThat(a.getAnswers()).isEqualTo(choice1);
        assertThat(b.getAnswers()).isEqualTo(choice2);
        assertThat(c.getAnswers()).isEqualTo(choice3);
    }

    @Test
    void getPollId() {
        assertThat(a.getPollId()).isEqualTo(1);
        assertThat(b.getPollId()).isEqualTo(1);
        assertThat(c.getPollId()).isEqualTo(2);
    }

    @Test
    void getUserId() {
        assertThat(a.getUserId()).isEqualTo(1);
        assertThat(b.getUserId()).isEqualTo(2);
        assertThat(c.getUserId()).isEqualTo(3);
    }

    @Test
    void setAnswers() {
        List<String> newAnswers = new ArrayList<>();
        newAnswers.add("Answer 4");
        a.setAnswers(newAnswers);

        assertThat(a.getAnswers()).isEqualTo(newAnswers);
    }

    @Test
    void setPollId() {
        int newId = 9;
        a.setPollId(newId);

        assertThat(a.getPollId()).isEqualTo(newId);
    }

    @Test
    void setUserId() {
        int newId = 9;
        a.setUserId(newId);

        assertThat(a.getUserId()).isEqualTo(newId);
    }

    @Test
    void testEquals() {
        assertThat(a).isNotEqualTo(b);
        assertThat(a).isEqualTo(a3);
    }

    @Test
    void testHashCode() {
        int hashed = a.hashCode();
        assertThat(hashed).isNotNull();
        assertThat(a.hashCode()).isEqualTo(a3.hashCode());
        assertThat(a.createAnswer().hashCode()).isEqualTo(a3.createAnswer().hashCode());
    }

    @Test
    void testToString() {
        String expected = "AnswerHelper(pollId=1, userId=1, answers=[Answer 1, Answer 2])";
        assertThat(a.toString()).isEqualTo(expected);
    }
}

