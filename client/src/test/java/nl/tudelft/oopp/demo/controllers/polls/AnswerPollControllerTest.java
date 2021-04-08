package nl.tudelft.oopp.demo.controllers.polls;

import java.io.IOException;
import java.util.Arrays;

import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import org.junit.jupiter.api.Test;


public class AnswerPollControllerTest {

    @Test
    void loadOpenData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if answering a open poll works:
        1. Create a poll.
        2. Choose the desired amount of questions (Max 10) to be loaded in.
        3. Open the poll from a user side.
        4. Check if the buttons are scaled correctly and all showing.
        5. Choose your options and submit.
         */
    }

    @Test
    void loadStatData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading the statistics for a poll works:
        1. Have a poll with some answers submitted to it.
        2. Set the poll on statistics and check it from a user side.
        3. Check if the buttons are scaled correctly and all showing.
        4. Check if the percentages for answers are correct.
         */
    }

    @Test
    void loadClosedData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading a closed poll loads in a special poll:
        1. Have a poll that is set to closed
        2. Load it into the answerPoll
           (This should normally not happen since it is already prevented).
        3. Make sure you get the special closed poll poll.
         */
    }

    @Test
    void submitAnswer() {
    /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if answering a poll multiple times doesn't work:
        1. Have a poll that's open
        2. Choose your answers and press submit.
        3. Check if the answers are submitted successfully.
         */
    }

    @Test
    void submitMultipleAnswers() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if answering a poll multiple times doesn't work:
        1. Have a poll that you have already answered.
        2. Choose your answers and press submit.
        3. Check if you get the "You have already answered" popup.
         */
    }
}
