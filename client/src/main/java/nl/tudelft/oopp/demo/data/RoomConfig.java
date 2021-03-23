package nl.tudelft.oopp.demo.data;

import lombok.Data;

@Data
public class RoomConfig {
    private int studentRefreshRate;
    private int modRefreshRate;
    private int questionCooldown;
    private int paceCooldown;

    /**
     * Initialises a Settings object.
     * @param studentRefreshRate refresh rate of questions for students
     * @param modRefreshRate refresh rate of questions for moderators
     * @param questionCooldown cooldown for students for asking questions
     * @param paceCooldown cooldown for students for setting the pace
     */
    public RoomConfig(int studentRefreshRate, int modRefreshRate, int questionCooldown, int paceCooldown) {
        this.studentRefreshRate = studentRefreshRate;
        this.modRefreshRate = modRefreshRate;
        this.questionCooldown = questionCooldown;
        this.paceCooldown = paceCooldown;
    }
}
