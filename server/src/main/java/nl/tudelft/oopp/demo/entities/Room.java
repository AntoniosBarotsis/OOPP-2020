package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import com.fasterxml.jackson.annotation.JsonIgnore;

import com.fasterxml.jackson.annotation.JsonProperty;
import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
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
import nl.tudelft.oopp.demo.entities.users.ElevatedUser;

/**
 * The Room class. Note that the set of banned IPs is not exposed to the client by default
 * and will require the use of a getter since it's sensitive information.
 */
@Entity(name = "Room")
@Table(name = "rooms")
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
    @OneToOne(cascade = CascadeType.MERGE, optional = false)
    @JoinColumn(name = "admin_id")
    private ElevatedUser admin;
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
    @JsonIgnore
    @Column(name = "elevated_password")
    private String elevatedPassword;
    @Column(name = "normal_password")
    private String normalPassword;

    /**
     * Instantiates a new Room. `startingDate` becomes the current date,
     * `bannedIps`, `moderators`, `questions` and `polls` get instantiated as empty HashSets,
     * `tooFast` and `tooSlow` get initialized to 0. Lastly, passwords are generated.
     *
     * @param title            the title
     * @param admin            the admin id
     */
    public Room(String title, ElevatedUser admin) {
        this.title = title;
        this.admin = admin;

        this.startingDate = new Date();
        this.bannedIps = new HashSet<>();
        this.moderators = new HashSet<>();
        this.questions = new HashSet<>();
        this.polls = new HashSet<>();
        this.tooFast = 0;
        this.tooSlow = 0;

        generatePassword();
    }

    /**
     * Instantiates a new Room.
     */
    public Room() {
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
     * Gets id.
     *
     * @return the id
     */
    public long getId() {
        return id;
    }

    /**
     * Sets id.
     *
     * @param id the id
     */
    public void setId(long id) {
        this.id = id;
    }

    /**
     * Gets title.
     *
     * @return the title
     */
    public String getTitle() {
        return title;
    }

    /**
     * Sets title.
     *
     * @param title the title
     */
    public void setTitle(String title) {
        this.title = title;
    }

    /**
     * Gets starting date.
     *
     * @return the starting date
     */
    public Date getStartingDate() {
        return startingDate;
    }

    /**
     * Sets starting date.
     *
     * @param startingDate the starting date
     */
    public void setStartingDate(Date startingDate) {
        this.startingDate = startingDate;
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

    /**
     * Sets admin.
     *
     * @param admin the admin
     */
    public void setAdmin(ElevatedUser admin) {
        this.admin = admin;
    }

    /**
     * Gets moderators.
     *
     * @return the moderators
     */
    public Set<ElevatedUser> getModerators() {
        return moderators;
    }

    /**
     * Sets moderators.
     *
     * @param moderators the moderators
     */
    public void setModerators(Set<ElevatedUser> moderators) {
        this.moderators = moderators;
    }

    /**
     * Gets banned ips.
     *
     * @return the banned ips
     */
    public Set<String> getBannedIps() {
        return bannedIps;
    }

    /**
     * Sets banned ips.
     *
     * @param bannedIps the banned ips
     */
    public void setBannedIps(Set<String> bannedIps) {
        this.bannedIps = bannedIps;
    }

    /**
     * Gets questions.
     *
     * @return the questions
     */
    public Set<Question> getQuestions() {
        return questions;
    }

    /**
     * Sets questions.
     *
     * @param questions the questions
     */
    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    /**
     * Gets questions.
     *
     * @return the questions
     */
    public Set<Poll> getPolls() {
        return polls;
    }

    /**
     * Sets questions.
     *
     * @param polls the questions
     */
    public void setPolls(Set<Poll> polls) {
        this.polls = polls;
    }

    /**
     * Gets too fast.
     *
     * @return the too fast
     */
    public int getTooFast() {
        return tooFast;
    }

    /**
     * Sets too fast.
     *
     * @param tooFast the too fast
     */
    public void setTooFast(int tooFast) {
        this.tooFast = tooFast;
    }

    /**
     * Gets too slow.
     *
     * @return the too slow
     */
    public int getTooSlow() {
        return tooSlow;
    }

    /**
     * Sets too slow.
     *
     * @param tooSlow the too slow
     */
    public void setTooSlow(int tooSlow) {
        this.tooSlow = tooSlow;
    }

    /**
     * Gets elevated password.
     *
     * @return the elevated password
     */
    public String getElevatedPassword() {
        return elevatedPassword;
    }

    /**
     * Sets elevated password.
     *
     * @param elevatedPassword the elevated password
     */
    public void setElevatedPassword(String elevatedPassword) {
        this.elevatedPassword = elevatedPassword;
    }

    /**
     * Gets normal password.
     *
     * @return the normal password
     */
    public String getNormalPassword() {
        return normalPassword;
    }

    /**
     * Sets normal password.
     *
     * @param normalPassword the normal password
     */
    public void setNormalPassword(String normalPassword) {
        this.normalPassword = normalPassword;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof Room)) {
            return false;
        }
        Room room = (Room) o;
        return id == room.id
            && tooFast == room.tooFast
            && tooSlow == room.tooSlow
            && Objects.equals(title, room.title)
            && Objects.equals(startingDate, room.startingDate)
            && Objects.equals(admin, room.admin)
            && Objects.equals(moderators, room.moderators)
            && Objects.equals(bannedIps, room.bannedIps)
            && Objects.equals(elevatedPassword, room.elevatedPassword)
            && Objects.equals(normalPassword, room.normalPassword);
    }

    @Override
    public int hashCode() {
        return Objects
            .hash(id, title, startingDate, admin, moderators, bannedIps, tooFast,
                tooSlow, elevatedPassword, normalPassword);
    }

    @Override
    public String toString() {
        return "Room{"
            + "id=" + id
            + ", title='" + title + '\''
            + ", startingDate=" + startingDate
            + ", admin=" + admin
            + ", moderators=" + moderators
            + ", bannedIps=" + bannedIps
            + ", tooFast=" + tooFast
            + ", tooSlow=" + tooSlow
            + ", elevatedPassword='" + elevatedPassword + '\''
            + ", normalPassword='" + normalPassword + '\''
            + '}';
    }
}

// TODO Add a rate limit for the clients
