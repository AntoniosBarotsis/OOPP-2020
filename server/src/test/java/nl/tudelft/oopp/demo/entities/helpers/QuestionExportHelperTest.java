package nl.tudelft.oopp.demo.entities.helpers;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionExportHelperTest {

    QuestionExportHelper a;
    Question a2;
    QuestionExportHelper b;
    Date time;

    @BeforeEach
    void setUp() {
        time = new Date();

        a = new QuestionExportHelper("Question", "Answer", time);
        b = new QuestionExportHelper("Question", "Answer", time);

        a2 = new Question("Question", new Student("Student", "ip"));
        a2.setAnswer("Answer");
    }

    @Test
    void of() {
        assertThat(QuestionExportHelper.of(a2)).isEqualTo(a);
    }

    @Test
    void getText() {
        assertThat(a2.getText()).isEqualTo("Question");
    }

    @Test
    void getAnswer() {
        assertThat(a.getAnswer()).isEqualTo("Answer");
    }

    @Test
    void getTimeCreated() {
        assertThat(a.getTimeCreated()).isEqualTo(time);
    }

    @Test
    void setText() {
        String replace = "New Question";
        a.setText(replace);

        assertThat(a.getText()).isEqualTo(replace);
    }

    @Test
    void setAnswer() {
        String replace = "New Answer";
        a.setAnswer(replace);

        assertThat(a.getAnswer()).isEqualTo(replace);
    }

    @Test
    void setTimeCreated() {
        Date replace = new Date();
        a.setTimeCreated(replace);

        assertThat(a.getTimeCreated()).isEqualTo(replace);
    }

    @Test
    void testEquals() {
        assertThat(a).isEqualTo(b);
    }

    @Test
    void testHashCode() {
        assertThat(a.hashCode()).isEqualTo(b.hashCode());
    }

    @Test
    void testToString() {
        String expected = "QuestionExportHelper(text=Question, answer=Answer, timeCreated=" + a.getTimeCreated() + ")";
        assertThat(a.toString()).isEqualTo(expected);
    }
}