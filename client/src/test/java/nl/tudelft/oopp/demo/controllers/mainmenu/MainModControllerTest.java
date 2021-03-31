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
    void buttonSettingsClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonSettings works:
        1. Launch the client application.
        2. Join a room.
        3. Click on top-right button "Settings".
        4. A new window with Settings should appear.
         */
    }

    @Test
    void buttonSimpleClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonSimple works:
        1. Launch the client application.
        2. Join a room.
        3. Click on bottom-left button "Simple view".
        4. All buttons apart from "Answered/Unanswered" button should disappear.
        5. Simplified questionViews should load in the listView.
        6. The button should change it's name to "Moderator View"
        7. If pressed again, the menu should return to the state it was before step 3.
         */
    }

    @Test
    void buttonStartEndClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonStartEnd works:
        1. Launch the client application.
        2. Join a room.
        3. This button should be available only for Users of type LECTURER.
        4. If the lecture is ongoing, the text should be "End lecture".
        5. If the lecture is not ongoing, the text should be "Start lecture".
        6. Upon pressing the button, the text should change.
        7. A request to the server to start/end lecture should be made.
        8. If changing lecture status was successful, the button text should remain unchanged.
        9. If it was unsuccessful, the text should revert back after the data is refetched.
         */
    }

    @Test
    void buttonMakePollsClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if pressing "Ask multiple choice question" works:
        1. Upon pressing the button, a new poll window should launch.
         */
    }

    @Test
    void buttonShowPollsClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonShowPolls works:
        1. Launch the client application.
        2. Join a room.
        3. This button should be visible in top left of the window.
        4. Upon pressing the button, polls should appear and buttons's text should change.
        5. Button "Show Unanswered/Answered" should become disabled.
        6. Upon pressing again, the questions should load and the text should change.
        7. The disabled button should become enabled again.
         */
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
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if exporting the room log works:
        1. Launch the client application.
        2. Join a room.
        3. Upon pressing MenuItem "Export room log", fileChooser window opens.
        4. Pick a location and file name. You can pick existing text file to overwrite it.
        5. Room log should get saved to chosen file.
        6. All resources are closed when writing is finished.
        7. User receives confirmation or error message when writing is finished.
         */
    }

    /**
     * See {@link #exportAllClicked()}.
     * See {@link #exportAnsweredClicked()}.
     * See {@link #exportTopClicked()}.
     * See {@link #exportLogClicked()}.
     */
    @Test
    void directoryChooser() {
        // Since this method cannot be tested on it's own, follow one of the linked test plans.
    }

    /**
     * See {@link #exportAllClicked()}.
     * See {@link #exportAnsweredClicked()}.
     * See {@link #exportTopClicked()}.
     * See {@link #exportLogClicked()}.
     */
    @Test
    void writeToFile() {
        // Since this method cannot be tested on it's own, follow one of the linked test plans.
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
    void repeatFetch() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void populateListView() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void loadQuestions() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void loadPolls() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void loadModQuestionView() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void loadSimpleQuestionView() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #loadData()}.
     */
    @Test
    void loadPollView() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #buttonSimpleClicked()}.
     */
    @Test
    void setButtonVisibility() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #buttonStartEndClicked()}.
     */
    @Test
    void changeOngoingLecture() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }
}