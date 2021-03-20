package nl.tudelft.oopp.demo.data;

import com.google.gson.annotations.JsonAdapter;
import java.util.Date;
import nl.tudelft.oopp.demo.data.deserializers.RoomInstanceCreator;

@JsonAdapter(RoomInstanceCreator.class)
public class Room {
    private long id;
    private String title;
    private Date startingDate;
    private boolean repeatingLecture;
    private int tooFast;
    private int tooSlow;
    private int normalSpeed;
    private boolean isOngoing;

    /**
     * Initializes a new room.
     * @param id id of room
     * @param title title of room
     * @param startingDate starting date of room
     * @param repeatingLecture repating of lecture
     * @param tooFast too fast pacer
     * @param tooSlow too slow pacer
     * @param normalSpeed normal speed pacer
     * @param isOngoing ongoing lecture
     */
    public Room(long id, String title, Date startingDate, boolean repeatingLecture,
                int tooFast, int tooSlow, int normalSpeed, boolean isOngoing) {
        this.id = id;
        this.title = title;
        this.startingDate = startingDate;
        this.repeatingLecture = repeatingLecture;
        this.tooFast = tooFast;
        this.tooSlow = tooSlow;
        this.normalSpeed = normalSpeed;
        this.isOngoing = isOngoing;
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

    /**
     * Getter for normal speed pace.
     * @return normal speed pace
     */
    public int getNormalSpeed() {
        return normalSpeed;
    }

    /**
     * Setter for normal speed pace.
     * @param normalSpeed normal speed pace
     */
    public void setNormalSpeed(int normalSpeed) {
        this.normalSpeed = normalSpeed;
    }

    /**
     * Getter for ongoing lecture.
     * @return ongoing lecture
     */
    public boolean isOngoing() {
        return isOngoing;
    }

    /**
     * Setter for ongoing lecture.
     * @param ongoing ongoing lecture
     */
    public void setOngoing(boolean ongoing) {
        isOngoing = ongoing;
    }
}
