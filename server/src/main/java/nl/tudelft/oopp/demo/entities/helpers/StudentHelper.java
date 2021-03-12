package nl.tudelft.oopp.demo.entities.helpers;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.Student;

@Data
@NoArgsConstructor
public class StudentHelper {
    private String username;
    private String ip;

    public Student createStudent() {
        return new Student(username, ip);
    }
}
