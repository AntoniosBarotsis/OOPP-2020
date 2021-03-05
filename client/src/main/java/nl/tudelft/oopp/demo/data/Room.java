package nl.tudelft.oopp.demo.data;

import java.util.Date;

public class Room {
    private long id;
    private String title;
    private Date startingDate;
    private boolean repeatingLecture;
    private int tooFast;
    private int tooSlow;

    /**
     * Initializes a new room.
     * @param id id of room
     * @param title title of room
     * @param startingDate starting date of room
     * @param repeatingLecture repating of lecture
     * @param tooFast too fast pacer
     * @param tooSlow too slow pacer
     */
    public Room(long id, String title, Date startingDate,
                boolean repeatingLecture, int tooFast, int tooSlow) {
        this.id = id;
        this.title = title;
        this.startingDate = startingDate;
        this.repeatingLecture = repeatingLecture;
        this.tooFast = tooFast;
        this.tooSlow = tooSlow;
    }

    /**
     * Getter for id of room.
     * @return id of room
     */
    public long getId() {
        return id;
    }

    /**
     * Getter for title of room.
     * @return title of room
     */
    public String getTitle() {
        return title;
    }

    /**
     * Getter for starting date of room.
     * @return starting date of room
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * Setter for starting date of room.
     * @param startingDate starting date of room
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
    }

    /**
     * Getter for repeating lecture.
     * @return repeating lecture
     */
    public boolean isRepeatingLecture() {
        return repeatingLecture;
    }

    /**
     * Setter for repeating lecture.
     * @param repeatingLecture repeating leture
     */
    public void setRepeatingLecture(boolean repeatingLecture) {
        this.repeatingLecture = repeatingLecture;
    }

    /**
     * Getter for too fast pace.
     * @return too fast pacer
     */
    public int getTooFast() {
        return tooFast;
    }

    /**
     * Setter for too fast pace.
     * @param tooFast too fast pace
     */
    public void setTooFast(int tooFast) {
        this.tooFast = tooFast;
    }

    /**
     * Getter for too slow pace.
     * @return too slow pace
     */
    public int getTooSlow() {
        return tooSlow;
    }

    /**
     * Setter for too slow pace.
     * @param tooSlow too slow pace
     */
    public void setTooSlow(int tooSlow) {
        this.tooSlow = tooSlow;
    }
}
