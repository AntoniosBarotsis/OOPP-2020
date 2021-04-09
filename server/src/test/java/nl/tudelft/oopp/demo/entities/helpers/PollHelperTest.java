package nl.tudelft.oopp.demo.entities.helpers;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class PollHelperTest {

    PollHelper a1 = new PollHelper();
    Poll a2;
    PollHelper b = new PollHelper();
    PollHelper c = new PollHelper();

    List<String> choice1;
    List<String> choice2;
    List<String> corrects;


    @BeforeEach
    void setUp() {

        choice1 = new ArrayList<>();
        choice2 = new ArrayList<>();
        corrects = new ArrayList<>();

        choice1.add("Answer 1");
        choice1.add("Answer 2");
        choice2.add("Answer 1");
        corrects.add("Answer 1");

        a1 = new PollHelper();
        a1.setText("Question 1");
        a1.setOptions(choice1);
        a1.setCorrectAnswer(corrects);

        a2 = new Poll("Question 1", choice1, corrects);

        b = new PollHelper();
        b.setText("Question 1");
        b.setOptions(choice1);
        b.setCorrectAnswer(corrects);

        c = new PollHelper();
        c.setText("Question 2");
        c.setOptions(choice2);
        c.setCorrectAnswer(corrects);
    }

    @Test
    void createPoll() {
        Poll createdPoll = a1.createPoll();
        assertEquals(createdPoll.getAnswers(), a2.getAnswers());
        assertEquals(createdPoll.getOptions(), a2.getOptions());
        assertEquals(createdPoll.getCorrectAnswer(), a2.getCorrectAnswer());
        assertEquals(createdPoll.getStatus(), a2.getStatus());
        assertEquals(createdPoll.getText(), a2.getText());
        assertEquals(createdPoll.getTimeCreated(), a2.getTimeCreated());
        assertEquals(createdPoll.getClass(), a2.getClass());
    }

    @Test
    void getText() {
        assertThat(a1.getText()).isEqualTo("Question 1");
    }

    @Test
    void getOptions() {
        List<String> d = new ArrayList<>();
        d.add("Answer 1");
        d.add("Answer 2");

        assertThat(a1.getOptions()).isEqualTo(d);
    }

    @Test
    void getCorrectAnswer() {
        List<String> d = new ArrayList<>();
        d.add("Answer 1");

        assertThat(a1.getCorrectAnswer()).isEqualTo(d);
    }

    @Test
    void setText() {
        String newQuestion = "New question";
        a1.setText(newQuestion);

        assertThat(a1.getText()).isEqualTo(newQuestion);
    }

    @Test
    void setOptions() {
        List<String> newOptions = new ArrayList<>();
        newOptions.add("Answer 4");
        a1.setOptions(newOptions);

        assertThat(a1.getOptions()).isEqualTo(newOptions);
    }

    @Test
    void setCorrectAnswer() {
        List<String> newCorrect = new ArrayList<>();
        newCorrect.add("Answer 4");
        a1.setCorrectAnswer(newCorrect);

        assertThat(a1.getCorrectAnswer()).isEqualTo(newCorrect);
    }

    @Test
    void testEquals() {
        assertThat(a1).isEqualTo(b);
        assertThat(a1).isNotEqualTo(c);
    }

    @Test
    void testHashCode() {
        assertThat(a1.hashCode()).isEqualTo(b.hashCode());
        assertThat(a1.hashCode()).isNotEqualTo(c.hashCode());
    }

    @Test
    void testToString() {
        String expected = "PollHelper(text=Question 1,"
                + "options=[Answer 1, Answer 2], correctAnswer=[Answer 1])";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}