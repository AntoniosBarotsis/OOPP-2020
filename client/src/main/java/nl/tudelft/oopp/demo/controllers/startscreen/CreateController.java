package nl.tudelft.oopp.demo.controllers.startscreen;

public class CreateController {

    /**
     * In/output from fxml file:
     * input_roomName : Where user inserts name of room
     * input_date : Where user inserts date of scheduled room (If not blank, room is scheduled)
     * input_time : Where user inserts time of scheduled room (If date is blank and time is not, date is today)
     * text_codeStudent : Where the code for students must be inserted
     * text_codeMod : Where the code for moderators must be inserted
     */

    /**
     * Joins the room that was just created (Should work just like the joinRoom but with already known room id
     */
    public void createButton() {
        System.out.println("Create works!");
    }
}
