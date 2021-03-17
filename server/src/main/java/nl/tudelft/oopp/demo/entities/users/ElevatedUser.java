package nl.tudelft.oopp.demo.entities.users;

import javax.persistence.Entity;

/**
 * The type Elevated user.
 */
@Entity
public class ElevatedUser extends User {
    /**
     * Instantiates a new Elevated user.
     *
     * @param username the username
     * @param ip       the ip
     * @param isAdmin  the is admin
     */
    public ElevatedUser(String username, String ip, boolean isAdmin) {
        super(username, ip);

        if (isAdmin) {
            setType(Type.ADMIN);
        } else {
            setType(Type.MODERATOR);
        }
    }

    /**
     * Instantiates a new Elevated user.
     *
     * @param username the username
     * @param ip       the ip
     */
    public ElevatedUser(String username, String ip) {
        super(username, ip);

        setType(Type.MODERATOR);
    }

    /**
     * Instantiates a new Elevated user.
     */
    public ElevatedUser() {

    }
}
