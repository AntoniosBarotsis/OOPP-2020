package nl.tudelft.oopp.demo.controllers.polls;

import org.junit.jupiter.api.Test;

class ModPollAskControllerTest {

    /**
     * Follow the manual test plan for fetching polls from server first.
     */
    @Test
    void loadData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a poll works:
        1. Upon launching the application and creating a room, the create multiple
           choice button should be available.
        2. When clicking that button, the settings for creating a poll should appear.
        2. Check that you can write the question text.
        3. Check that you can type in different answers
        4. Check that you can mark each answer as true/false.
         */
    }

    @Test
    void testSelectors() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a poll works:
        1. Upon launching the application and creating a room, the create multiple
           choice button should be available.
        2. When clicking that button, the settings for creating a poll should appear.
        3. Test that each selector for true/false actually works by clicking on them.
         */
    }

    @Test
    void testSubmitButton() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a poll works:
        1. Upon launching the application and creating a room, the create multiple
           choice button should be available.
        2. When clicking that button, the settings for creating a poll should appear.
        3. Test that you get an error pop-up if there are no true answers.
        4. Test that you can't submit a poll with an empty question.
        5. Check that after doing everything correctly the buttons close and show statistics
            are unlocked and that submit disappears.
        6. Check that the new poll appears in the poll list when clicking the show polls button
        */
    }

    @Test
    void testCloseButton() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a poll works:
        1. Upon launching the application and creating a room, the create multiple
           choice button should be available.
        2. When clicking that button, the settings for creating a poll should appear.
        3. After submitting, check that you can close the poll, which will show
            the submit button again and disable the close button.
         */
    }

    @Test
    void testStatisticsButton() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a poll works:
        1. Upon launching the application and creating a room, the create multiple
           choice button should be available.
        2. When clicking that button, the settings for creating a poll should appear.
        3. After submitting, check that you can see the statistics by clicking on the button.
         */
    }

}