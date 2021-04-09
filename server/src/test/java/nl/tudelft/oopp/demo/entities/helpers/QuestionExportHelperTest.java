package nl.tudelft.oopp.demo.entities.helpers;

import java.util.Date;
import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class QuestionExportHelperTest {

    QuestionExportHelper a1;
    Question a2;
    QuestionExportHelper b1;
    Date time;

    @BeforeEach
    void setUp() {
        time = new Date();

        a1 = new QuestionExportHelper("Question", "Answer", time);
        b1 = new QuestionExportHelper("Question", "Answer", time);

        a2 = new Question("Question", new Student("Student", "ip"));
        a2.setAnswer("Answer");
    }

    @Test
    void of() {
        assertThat(QuestionExportHelper.of(a2)).isEqualTo(a1);
    }

    @Test
    void getText() {
        assertThat(a2.getText()).isEqualTo("Question");
    }

    @Test
    void getAnswer() {
        assertThat(a1.getAnswer()).isEqualTo("Answer");
    }

    @Test
    void getTimeCreated() {
        assertThat(a1.getTimeCreated()).isEqualTo(time);
    }

    @Test
    void setText() {
        String replace = "New Question";
        a1.setText(replace);

        assertThat(a1.getText()).isEqualTo(replace);
    }

    @Test
    void setAnswer() {
        String replace = "New Answer";
        a1.setAnswer(replace);

        assertThat(a1.getAnswer()).isEqualTo(replace);
    }

    @Test
    void setTimeCreated() {
        Date replace = new Date();
        a1.setTimeCreated(replace);

        assertThat(a1.getTimeCreated()).isEqualTo(replace);
    }

    @Test
    void testEquals() {
        assertThat(a1).isEqualTo(b1);
    }

    @Test
    void testHashCode() {
        assertThat(a1.hashCode()).isEqualTo(b1.hashCode());
    }

    @Test
    void testToString() {
        String expected = "QuestionExportHelper(text=Question, answer=Answer, "
                + "timeCreated=" + a1.getTimeCreated() + ")";
        assertThat(a1.toString()).isEqualTo(expected);
    }
}