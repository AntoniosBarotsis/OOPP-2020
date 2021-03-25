package nl.tudelft.oopp.demo.entities;

import static javax.persistence.GenerationType.SEQUENCE;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import lombok.Data;

/**
 * The type Room config.
 */
@Data
//@NoArgsConstructor
@Entity(name = "RoomConfig")
@Table(name = "room_config")
public class RoomConfig {
    @Id
    @SequenceGenerator(
        name = "room_config_sequence",
        sequenceName = "room_config_sequence",
        allocationSize = 1
    )
    @GeneratedValue(
        strategy = SEQUENCE,
        generator = "room_config_sequence"
    )
    private int id;
    @Column(name = "student_refresh_rate")
    private int studentRefreshRate;
    @Column(name = "mod_refresh_rate")
    private int modRefreshRate;
    @Column(name = "question_cooldown")
    private int questionCooldown;
    @Column(name = "pace_cooldown")
    private int paceCooldown;


    /**
     * Instantiates a new Room config.
     */
    public RoomConfig() {
        this.studentRefreshRate = 5;
        this.modRefreshRate = 5;
        this.questionCooldown = 300;
        this.paceCooldown = 300;
    }
}
