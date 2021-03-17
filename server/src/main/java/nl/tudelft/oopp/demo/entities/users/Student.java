package nl.tudelft.oopp.demo.entities.users;

import javax.persistence.Entity;
import lombok.NoArgsConstructor;

/**
 * The type Student.
 */
@Entity
@NoArgsConstructor
public class Student extends User {
    /**
     * Instantiates a new Student.
     *
     * @param username the username
     * @param ip       the ip
     */
    public Student(String username, String ip) {
        super(username, ip);

        setType(Type.STUDENT);
    }
}
