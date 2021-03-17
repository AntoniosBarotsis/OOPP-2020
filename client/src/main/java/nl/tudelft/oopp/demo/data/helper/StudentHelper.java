package nl.tudelft.oopp.demo.data.helper;

public class StudentHelper {
    private String username;
    private String ip;

    /**
     * Initialises a helper student used for sending json data.
     * @param username username of student
     * @param ip ip address of student
     */
    public StudentHelper(String username, String ip) {
        this.username = username;
        this.ip = ip;
    }
}