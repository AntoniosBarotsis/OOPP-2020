package nl.tudelft.oopp.demo.entities.helpers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.*;

import nl.tudelft.oopp.demo.entities.Question;
import nl.tudelft.oopp.demo.entities.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class QuestionHelperTest {
    private QuestionHelper qh1;
    private QuestionHelper qh2;
    private StudentHelper sh1;
    private Question q1;

    @BeforeEach
    void setUp() {
        qh1 = new QuestionHelper();

        sh1 = new StudentHelper();
        sh1.setUsername("username");
        sh1.setIp("ip");

        qh1.setText("text");
        qh1.setAuthor(sh1);

        qh2 = new QuestionHelper();
        q1 = new Question("text", sh1.createStudent());
    }

    @Test
    void createQuestion() {
        assertThat(qh1.createQuestion()).isEqualTo(q1);
    }

    @Test
    void getText() {
        assertThat(qh1.getText()).isEqualTo("text");
    }

    @Test
    void getAuthor() {
        assertThat(qh1.getAuthor()).isEqualTo(sh1);
    }

    @Test
    void setText() {
        qh1.setText("text 2");
        assertThat(qh1.getText()).isEqualTo("text 2");
    }

    @Test
    void setAuthor() {
        StudentHelper sh2 = new StudentHelper();
        sh2.setUsername("username");
        sh2.setIp("ip");
        qh1.setText("text");
        qh1.setAuthor(sh2);
        assertThat(qh1.getAuthor()).isEqualTo(sh2);
    }

    @Test
    void testEquals() {
        assertThat(qh1).isNotEqualTo(qh2);
        qh2.setText("text");
        assertThat(qh1).isNotEqualTo(qh2);
        qh2.setAuthor(sh1);
        assertThat(qh1).isEqualTo(qh2);
    }

    @Test
    void testHashCode() {
        assertThat(qh1.hashCode()).isEqualTo(qh1.hashCode());
        assertThat(qh1.hashCode()).isNotEqualTo(qh2.hashCode());
    }

    @Test
    void testToString() {
        assertThat(qh1.toString()).isEqualTo("QuestionHelper(text=text, "
            + "author=StudentHelper(username=username, ip=ip))");
    }
}