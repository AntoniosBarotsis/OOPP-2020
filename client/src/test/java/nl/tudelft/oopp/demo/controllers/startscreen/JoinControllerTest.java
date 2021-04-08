package nl.tudelft.oopp.demo.controllers.startscreen;

import org.junit.jupiter.api.Test;

public class JoinControllerTest {

    @Test
    void joinButtonTest(){
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if joining a room works:
        1. Launch the start application.
        2. Press Join room.
        3. Fill the fields.
        4. See if it joins the correct room.
        5. If there is an issue, alerts should appear and explain what is the problem.
        6. If a password for students was inserted, #startMainStudentMenu should execute.
        7. else #startMainModMenu should execute.
         */
    }

    @Test
    void buttonBack() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if buttonBack works:
        1. Launch the start application.
        2. Press join room.
        3. Press back; You should return to the previous start window.
         */
    }

    /**
     * See {@link #joinButtonTest()}.
     */
    @Test
    void startMainModMenu() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #joinButtonTest()}.
     */
    @Test
    void startMainStudentMenu() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }

    /**
     * See {@link #joinButtonTest()}.
     */
    @Test
    void showAlert() {
        // Since this method cannot be tested on it's own, follow the linked test plan.
    }
}
