package nl.tudelft.oopp.demo.entities.users;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;


@ExtendWith(SpringExtension.class)
@DataJpaTest
class StudentTest {

    @Test
    void testConstructor() {
        assertDoesNotThrow(() -> new Student("Admin", "ip"));
    }
}