package nl.tudelft.oopp.demo.data;

public class QuestionAuthor {
    private long id;
    private String username;

    /**
     * Initialises a questionAuthor used for fetching json data.
     * @param id id of question author
     * @param username username of question author
     */
    public QuestionAuthor(long id, String username) {
        this.username = username;
        this.id = id;
    }

    /**
     * Getter for author id.
     * @return id of question author
     */
    public long getId() {
        return id;
    }

    /**
     * Setter for author id.
     * @param id id of question author
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Getter for author username.
     * @return username of question author
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for author username.
     * @param username username of question author
     */
    public void setUsername(String username) {
        this.username = username;
    }
}
