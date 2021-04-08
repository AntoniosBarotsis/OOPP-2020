package nl.tudelft.oopp.demo.entities.helpers;

import java.util.ArrayList;
import java.util.List;
import nl.tudelft.oopp.demo.entities.Poll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.parameters.P;

import static org.assertj.core.api.Assertions.assertThat;

class PollHelperTest {

    PollHelper a = new PollHelper();
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

        a = new PollHelper();
        a.setText("Question 1");
        a.setOptions(choice1);
        a.setCorrectAnswer(corrects);

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
        assertThat(a.createPoll()).isEqualTo(a2);
    }

    @Test
    void getText() {
        assertThat(a.getText()).isEqualTo("Question 1");
    }

    @Test
    void getOptions() {
        List<String> d = new ArrayList<>();
        d.add("Answer 1");
        d.add("Answer 2");

        assertThat(a.getOptions()).isEqualTo(d);
    }

    @Test
    void getCorrectAnswer() {
        List<String> d = new ArrayList<>();
        d.add("Answer 1");

        assertThat(a.getCorrectAnswer()).isEqualTo(d);
    }

    @Test
    void setText() {
        String newQuestion = "New question";
        a.setText(newQuestion);

        assertThat(a.getText()).isEqualTo(newQuestion);
    }

    @Test
    void setOptions() {
        List<String> newOptions = new ArrayList<>();
        newOptions.add("Answer 4");
        a.setOptions(newOptions);

        assertThat(a.getOptions()).isEqualTo(newOptions);
    }

    @Test
    void setCorrectAnswer() {
        List<String> newCorrect = new ArrayList<>();
        newCorrect.add("Answer 4");
        a.setCorrectAnswer(newCorrect);

        assertThat(a.getCorrectAnswer()).isEqualTo(newCorrect);
    }

    @Test
    void testEquals() {
        assertThat(a).isEqualTo(b);
        assertThat(a).isNotEqualTo(c);
    }

    @Test
    void testHashCode() {
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
        assertThat(a.hashCode()).isNotEqualTo(c.hashCode());
    }

    @Test
    void testToString() {
        String expected = "PollHelper(text=Question 1, options=[Answer 1, Answer 2], correctAnswer=[Answer 1])";
        assertThat(a.toString()).isEqualTo(expected);
    }
}