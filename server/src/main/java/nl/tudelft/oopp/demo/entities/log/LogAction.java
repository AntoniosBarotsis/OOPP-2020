package nl.tudelft.oopp.demo.entities.log;

/**
 * The type Log action.
 */
class LogAction {
    private ActionType type;
    private Object object;

    /**
     * The enum Action type.
     */
    enum ActionType {
        /**
         * Banned action type.
         */
        BANNED,
        /**
         * Asked action type.
         */
        ASKED,
        JOINED
    }

    /**
     * Instantiates a new Log action.
     *
     * @param type   the type
     * @param object the object
     */
    public LogAction(ActionType type, Object object) {
        this.type = type;
        this.object = object;
    }

    /**
     * Gets type.
     *
     * @return the type
     */
    public ActionType getType() {
        return type;
    }

    /**
     * Sets type.
     *
     * @param type the type
     */
    public void setType(ActionType type) {
        this.type = type;
    }

    /**
     * Gets object.
     *
     * @return the object
     */
    public Object getObject() {
        return object;
    }

    /**
     * Sets object.
     *
     * @param object the object
     */
    public void setObject(Object object) {
        this.object = object;
    }
}
