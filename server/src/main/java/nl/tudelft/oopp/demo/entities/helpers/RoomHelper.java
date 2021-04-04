package nl.tudelft.oopp.demo.entities.helpers;

import com.fasterxml.jackson.annotation.JsonFormat;

import java.util.Date;

import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.RoomConfig;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;

/**
 * The type Room helper.
 */
@Data
@NoArgsConstructor
public class RoomHelper {
    private String title;
    private String username;
    private boolean repeatingLecture;
    private RoomConfig roomConfig;
    @JsonFormat (pattern = "yyyy-MM-dd hh:mm:ss")
    private Date startingDate;
    @JsonFormat (pattern = "yyyy-MM-dd hh:mm:ss")
    private Date endingDate;

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
                      RoomConfig roomConfig, Date startingDate, Date endingDate) {
        this.title = title;
        this.username = username;
        this.repeatingLecture = repeatingLecture;
        this.roomConfig = roomConfig;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    /**
     * Instantiates a new Room helper.
     *
     * @param title            the title
     * @param username         the username
     * @param repeatingLecture the repeating lecture
     * @param startingDate     the starting date
     * @param endingDate       the ending date
     */
    public RoomHelper(String title, String username, boolean repeatingLecture,
                      Date startingDate, Date endingDate) {
        this.title = title;
        this.username = username;
        this.repeatingLecture = repeatingLecture;
        this.startingDate = startingDate;
        this.endingDate = endingDate;
    }

    /**
     * Create room room.
     *
     * @param admin the admin
     * @return the room
     */
    public Room createRoom(ElevatedUser admin) {
        Room room = new Room(title, repeatingLecture, admin, roomConfig);
        room.setStartingDate(startingDate);
        room.setEndingDate(endingDate);

        return room;
    }
}
