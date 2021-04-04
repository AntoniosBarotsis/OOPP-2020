package nl.tudelft.oopp.demo.data.helper;

import nl.tudelft.oopp.demo.data.RoomConfig;

public class RoomHelper {
    private String title;
    private String username;
    private boolean repeatingLecture;
    private RoomConfig roomConfig;
    private String startingDate;
    private String endingDate;

    /**
     * Instantiates a new Room helper.
     *
     * @param title            the title
     * @param username         the username
     * @param repeatingLecture the repeating lecture
     * @param roomConfig       the room config
     * @param startingDate     the starting date
     * @param endingDate       the ending date
     */
    public RoomHelper(String title, String username, boolean repeatingLecture,
                      RoomConfig roomConfig, String startingDate, String endingDate) {
        this.title = title;
        this.username = username;
        this.repeatingLecture = repeatingLecture;
        this.roomConfig = roomConfig;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    /**
     * Getter for title.
     * @return title of room
     */
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Getter for admin username.
     * @return username of admin
     */
    public String getUsername() {
        return username;
    }

    /**
     * Setter for admin username.
     * @param username username of admin
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /**
     * Getter for repeating lecture.
     * @return true if lecture is repeating
     */
    public boolean isRepeatingLecture() {
        return repeatingLecture;
    }

    /**
     * Setter for repeating lecture.
     * @param repeatingLecture if lecture is repeating
     */
    public void setRepeatingLecture(boolean repeatingLecture) {
        this.repeatingLecture = repeatingLecture;
    }

    /**
     * Getter for room configurations.
     * @return configurations of room
     */
    public RoomConfig getRoomConfig() {
        return roomConfig;
    }

    /**
     * Setter for room configurations.
     * @param roomConfig new room configurations
     */
    public void setRoomConfig(RoomConfig roomConfig) {
        this.roomConfig = roomConfig;
    }

    /**
     * Getter for starting date of room.
     * @return starting date of room
     */
    public String getStartingDate() {
        return startingDate;
    }

    /**
     * Setter for starting date of room.
     * @param startingDate new starting date
     */
    public void setStartingDate(String startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Getter for ending date of room.
     * @return ending date of room
     */
    public String getEndingDate() {
        return endingDate;
    }

    /**
     * Setter for ending date of room.
     * @param endingDate new ending date
     */
    public void setEndingDate(String endingDate) {
        this.endingDate = endingDate;
    }
}
