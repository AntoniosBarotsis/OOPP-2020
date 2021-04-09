package nl.tudelft.oopp.demo.entities;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class AnswerTest {

    Answer a1;
    Answer a2;
    Answer b1;
    Answer c1;

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

        a1 = new Answer(choice1, 1, 1);
        a2 = new Answer(choice1, 1, 1);
        b1 = new Answer(choice2, 1, 2);
        c1 = new Answer(choice3, 2, 3);
    }

    @Test
    void getId() {
        assertThat(a1.getId()).isNotNull();
        assertThat(b1.getId()).isEqualTo(a1.getId());
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
    void setId() {
        int newId = 9;
        a1.setId(newId);

        assertThat(a1.getId()).isEqualTo(newId);
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
        assertThat(a1).isEqualTo(a2);
    }

    @Test
    void hash() {
        int hashed = a1.hashCode();
        assertThat(hashed).isNotNull();
        assertThat(a1.hashCode()).isEqualTo(a2.hashCode());
    }

    @Test
    void testToString() {
        String expected = "Answer(id=0, answers=[Answer 1, Answer 2], pollId=1, userId=1)";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}