package nl.tudelft.oopp.demo.data;

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

    /**
     * Getter for refresh rate of questions for students.
     * @return refresh rate of questions for students
     */
    public int getStudentRefreshRate() {
        return studentRefreshRate;
    }

    /**
     * Setter for refresh rate of questions for students.
     * @param studentRefreshRate new refresh rate
     */
    public void setStudentRefreshRate(int studentRefreshRate) {
        this.studentRefreshRate = studentRefreshRate;
    }

    /**
     * Getter for refresh rate of questions for moderators.
     * @return refresh rate of questions for moderators
     */
    public int getModRefreshRate() {
        return modRefreshRate;
    }

    /**
     * Setter for refresh rate of questions for moderators.
     * @param modRefreshRate new refresh rate
     */
    public void setModRefreshRate(int modRefreshRate) {
        this.modRefreshRate = modRefreshRate;
    }

    /**
     * Getter for cooldown for students for asking questions.
     * @return cooldown for students for asking questions
     */
    public int getQuestionCooldown() {
        return questionCooldown;
    }

    /**
     * Setter for cooldown for students for asking questions.
     * @param questionCooldown new cooldown
     */
    public void setQuestionCooldown(int questionCooldown) {
        this.questionCooldown = questionCooldown;
    }

    /**
     * Getter for cooldown for students for setting the pace.
     * @return cooldown for students for setting the pace
     */
    public int getPaceCooldown() {
        return paceCooldown;
    }

    /**
     * Setter for cooldown for students for setting the pace.
     * @param paceCooldown new cooldown
     */
    public void setPaceCooldown(int paceCooldown) {
        this.paceCooldown = paceCooldown;
    }
}
