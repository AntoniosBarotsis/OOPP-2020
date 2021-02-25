package nl.tudelft.oopp.demo.entities.users;

import javax.persistence.Entity;

/**
 * The type Student.
 */
@Entity
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

    /**
     * Instantiates a new Student.
     */
    public Student() {

    }
}
