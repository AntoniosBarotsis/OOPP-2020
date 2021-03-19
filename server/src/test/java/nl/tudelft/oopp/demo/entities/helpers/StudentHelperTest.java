package nl.tudelft.oopp.demo.entities.helpers;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.springframework.test.annotation.DirtiesContext.MethodMode.BEFORE_METHOD;

import nl.tudelft.oopp.demo.entities.users.Student;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.test.annotation.DirtiesContext;

class StudentHelperTest {
    private StudentHelper sh1;
    private StudentHelper sh2;
    private Student s1;

    @BeforeEach
    void setUp() {
        sh1 = new StudentHelper();
        sh1.setUsername("username");
        sh1.setIp("ip");
        sh2 = new StudentHelper();
        s1 = new Student("username", "ip");
    }

    @Test
    void createStudent() {
        assertThat(sh1.createStudent()).isEqualTo(s1);
    }

    @Test
    void getUsername() {
        assertThat(sh1.getUsername()).isEqualTo("username");
    }

    @Test
    void getIp() {
        assertThat(sh1.getIp()).isEqualTo("ip");
    }

    @Test
    void setUsername() {
        sh1.setUsername("username 2");
        assertThat(sh1.createStudent().getUsername()).isEqualTo("username 2");
    }

    @Test
    void setIp() {
        sh1.setIp("ip 2");
        assertThat(sh1.createStudent().getIp()).isEqualTo("ip 2");
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void testEquals() {
        assertThat(sh1).isNotEqualTo(sh2);
        sh2.setIp("ip");
        assertThat(sh1).isNotEqualTo(sh2);
        sh2.setUsername("username");
        assertThat(sh1).isEqualTo(sh2);
    }

    @Test
    void testHashCode() {
        assertThat(sh1.hashCode()).isEqualTo(sh1.hashCode());
        assertThat(sh1.hashCode()).isNotEqualTo(sh2.hashCode());
    }

    @Test
    @DirtiesContext(methodMode = BEFORE_METHOD)
    void testToString() {
        assertThat(sh1.toString()).isEqualTo("StudentHelper(username=username, ip=ip)");
    }
}