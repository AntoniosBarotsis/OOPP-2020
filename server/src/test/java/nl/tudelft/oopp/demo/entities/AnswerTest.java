package nl.tudelft.oopp.demo.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    Answer a;
    Answer a2;
    Answer b;
    Answer c;

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

        a = new Answer(choice1, 1, 1);
        a2 = new Answer(choice1, 1, 1);
        b = new Answer(choice2, 1, 2);
        c = new Answer(choice3, 2, 3);
    }

    @Test
    void getId() {
        assertThat(a.getId()).isNotNull();
        assertThat(b.getId()).isEqualTo(a.getId());
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
    void setId() {
        int newId = 9;
        a.setId(newId);

        assertThat(a.getId()).isEqualTo(newId);
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
        assertThat(a).isEqualTo(a2);
    }

    @Test
    void hash() {
        int hashed = a.hashCode();
        assertThat(hashed).isNotNull();
        assertThat(a.hashCode()).isEqualTo(a2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Answer(id=0, answers=[Answer 1, Answer 2], pollId=1, userId=1)";
        assertThat(a.toString()).isEqualTo(expected);
    }
}