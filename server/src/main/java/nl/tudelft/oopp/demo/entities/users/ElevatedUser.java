package nl.tudelft.oopp.demo.entities.users;

import java.util.Objects;
import javax.persistence.Entity;

/**
 * The type Elevated user.
 */
@Entity
public class ElevatedUser extends User {
    private boolean isAdmin;

    /**
     * Instantiates a new Elevated user.
     *
     * @param username the username
     * @param ip       the ip
     * @param isAdmin  the is admin
     */
    public ElevatedUser(String username, String ip, boolean isAdmin) {
        super(username, ip);
        this.isAdmin = isAdmin;
        setType(Type.ADMIN);

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
        this.isAdmin = false;

        setType(Type.MODERATOR);
    }

    /**
     * Instantiates a new Elevated user.
     */
    public ElevatedUser() {

    }

    /**
     * Is admin boolean.
     *
     * @return the boolean
     */
    public boolean isAdmin() {
        return isAdmin;
    }

    /**
     * Sets is admin.
     *
     * @param isAdmin the is admin
     */
    public void setIsAdmin(boolean isAdmin) {
        this.isAdmin = isAdmin;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof ElevatedUser)) {
            return false;
        }
        if (!super.equals(o)) {
            return false;
        }
        ElevatedUser that = (ElevatedUser) o;
        return isAdmin == that.isAdmin;
    }

    @Override
    public int hashCode() {
        return Objects.hash(super.hashCode(), isAdmin);
    }

    @Override
    public String toString() {
        return "ElevatedUser{"
            + "isAdmin=" + isAdmin
            + '}';
    }
}
