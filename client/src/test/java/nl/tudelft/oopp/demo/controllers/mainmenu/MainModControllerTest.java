package nl.tudelft.oopp.demo.controllers.mainmenu;

import org.junit.jupiter.api.Test;

class MainModControllerTest {

    @Test
    void loadData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the client application.
        2. Join a room.
        3. Upon joining tooFast, tooSlow and Normal labels should be successfully set.
        4. The questions for the chosen room should appear as a list in the centre of the window.
         */
    }

    @Test
    void buttonLinksClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonLinks works:
        1. Launch the client application.
        2. Join a room.
        3. Click on top-right button "Links".
        4. An informational dialog should appear, which shows code for students and moderators.
         */
    }

    @Test
    void buttonMakePollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonShowPollsClicked() {
        //To be implemented.
    }

    @Test
    void buttonAnsweredClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if button Answered Questions works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing the button it's text should change to "Unanswered questions".
        4. Only answered questions should be displayed in the listView.
        5. If you press again, unanswered questions should appear and button text should change.
         */
    }

    @Test
    void exportAllClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if exporting all questions works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing MenuItem "Export all questions", fileChooser window opens.
        4. Pick a location and file name. You can pick existing text file to overwrite it.
        5. All questions get fetched and saved to chosen file.
        6. All resources are closed when writing is finished.
        7. User receives confirmation or error message when writing is finished.
         */
    }

    @Test
    void exportTopClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if exporting top 20 questions works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing MenuItem "Export top 20 questions", fileChooser window opens.
        4. Pick a location and file name. You can pick existing text file to overwrite it.
        5. Top 20 questions get fetched and saved to chosen file.
        6. All resources are closed when writing is finished.
        7. User receives confirmation or error message when writing is finished.
         */
    }

    @Test
    void exportAnsweredClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if exporting questions with text answer works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing MenuItem "Export answered questions", fileChooser window opens.
        4. Pick a location and file name. You can pick existing text file to overwrite it.
        5. Questions with text answer get fetched and saved to chosen file.
        6. All resources are closed when writing is finished.
        7. User receives confirmation or error message when writing is finished.
         */
    }

    @Test
    void exportLogClicked() {
        //To be implemented.
    }

    /**
     * See {@link #exportAllClicked()}.
     * See {@link #exportAnsweredClicked()}.
     * See {@link #exportTopClicked()}.
     * See {@link #exportLogClicked()}.
     */
    @Test
    void directoryChooser() {
        // Since this method cannot be tested on it's own, follow one of the linked test plan.
    }

    /**
     * See {@link #exportAllClicked()}.
     * See {@link #exportAnsweredClicked()}.
     * See {@link #exportTopClicked()}.
     * See {@link #exportLogClicked()}.
     */
    @Test
    void writeToFile() {
        // Since this method cannot be tested on it's own, follow one of the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void fetchData() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void populateListView() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }
}