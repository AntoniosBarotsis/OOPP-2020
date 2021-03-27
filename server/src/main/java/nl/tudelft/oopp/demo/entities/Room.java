package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;

/**
 * The Room class. Note that the set of banned IPs is not exposed to the client by default
 * and will require the use of a getter since it's sensitive information.
 */
@Entity(name = "Room")
@Table(name = "rooms")
@Data
@NoArgsConstructor
public class Room {
    @Id
    @SequenceGenerator(
        name = "room_sequence",
        sequenceName = "room_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "room_sequence"
    )
    @Column(name = "id", updatable = false)
    private long id;
    @Column(name = "title")
    private String title;
    @Column(name = "starting_date")
    private Date startingDate;
    @Column(name = "repeating_lecture")
    private boolean repeatingLecture;
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "admin_id")
    private ElevatedUser admin;
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "room_config_id")
    private RoomConfig roomConfig;
    @OneToMany
    @Column(name = "moderators")
    private Set<ElevatedUser> moderators;
    @JsonIgnore
    @ElementCollection
    @Column(name = "banned_ips")
    private Set<String> bannedIps;
    @OneToMany
    @Column(name = "questions")
    private Set<Question> questions;
    @OneToMany
    @Column(name = "polls")
    private Set<Poll> polls;
    @Column(name = "too_fast")
    private int tooFast;
    @Column(name = "too_slow")
    private int tooSlow;
    @Column(name = "normal_speed")
    private int normalSpeed;
    @JsonIgnore
    @Column(name = "elevated_password")
    private String elevatedPassword;
    @Column(name = "normal_password")
    private String normalPassword;
    @Column(name = "is_ongoing")
    private boolean isOngoing;

    /**
     * Instantiates a new Room. `startingDate` becomes the current date,
     * `bannedIps`, `moderators`, `questions` and `polls` get instantiated as empty HashSets,
     * `tooFast`, `tooSlow` as well as `normalSpeed` get initialized to 0. Lastly, passwords and a
     *  RoomConfig are generated. The admin's IP gets added to the moderator IPs
     *
     * @param title            the title
     * @param repeatingLecture the repeating lecture
     * @param admin            the admin id
     */
    public Room(String title, boolean repeatingLecture, ElevatedUser admin) {
        this.title = title;
        this.repeatingLecture = repeatingLecture;
        this.admin = admin;

        this.startingDate = new Date();
        this.bannedIps = new HashSet<>();
        this.roomConfig = new RoomConfig();
        Set<ElevatedUser> ips = new HashSet<>();
        ips.add(admin);
        this.moderators = ips;
        this.questions = new HashSet<>();
        this.polls = new HashSet<>();
        this.tooFast = 0;
        this.tooSlow = 0;
        this.normalSpeed = 0;
        this.isOngoing = false;

        generatePassword();
    }

    /**
     * Same as the previous constructor except the `roomConfig` is specified.
     *
     * @param title            the title
     * @param repeatingLecture the repeating lecture
     * @param admin            the admin
     * @param roomConfig       the room config
     */
    public Room(String title, boolean repeatingLecture, ElevatedUser admin, RoomConfig roomConfig) {
        this.title = title;
        this.repeatingLecture = repeatingLecture;
        this.admin = admin;

        this.startingDate = new Date();
        this.bannedIps = new HashSet<>();
        this.roomConfig = roomConfig;
        Set<ElevatedUser> ips = new HashSet<>();
        ips.add(admin);
        this.moderators = ips;
        this.questions = new HashSet<>();
        this.polls = new HashSet<>();
        this.tooFast = 0;
        this.tooSlow = 0;
        this.normalSpeed = 0;
        this.isOngoing = false;

        generatePassword();
    }

    /**
     * Generates an elevated user password and a normal password by creating a random UUID and
     * splitting it in 2. Dashes are removed to make the passwords cleaner.
     */
    private void generatePassword() {
        String uuid = UUID.randomUUID().toString();

        String elevatedPassword = uuid.substring(0, uuid.length() / 2);
        String normalPassword = uuid.substring(uuid.length() / 2);

        this.elevatedPassword = elevatedPassword.replace("-", "");
        this.normalPassword = normalPassword.replace("-", "");
    }

    /**
     * Gets admin id.
     *
     * @return the admin id
     */
    @JsonProperty("AdminId")
    public long getAdmin() {
        return admin.getId();
    }
}
