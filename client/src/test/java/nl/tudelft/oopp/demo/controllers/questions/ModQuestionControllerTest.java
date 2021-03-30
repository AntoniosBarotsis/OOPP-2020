package nl.tudelft.oopp.demo.controllers.questions;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

class ModQuestionControllerTest {

    @Test
    void loadData() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. Check that the question view is created and
            everything works accordingly in the moderator view.
         */
    }

    @Test
    void questionAnswered() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if a question being marked as answered works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. In the moderator view, marking that question as answered should delete it from
            the main feed and move it to answered questions only menu.
         */
    }

    @Test
    void edit() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if editing a question works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. Upon pressing the edit button, the field containing the text should unlock itself
            and be available for edition until the ENTER key is pressed
        7. That will make the text not editable again and set the new question
            to the modified text.
         */
    }

    @Test
    void getModified() {
        ModQuestionController contr = new ModQuestionController();
        assertEquals(false, contr.getModified());
    }

    @Test
    void banUser() {
        // Method still to be implemented
    }

    @Test
    void answer() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the server
        2. Launch the client application three times.
        3. Create a room in one of the windows.
        4. Join that room in another window as a student.
        5. Use the third window to join as another moderator.
        5. Ask a question as a student.
        6. Upon pressing the answer button in one of the windows,
            the answer field will be available for edition until the ENTER key is pressed.
        7. During that time the app will lock that question for the rest of the moderators,
            who won't be able to answer since a mod is already working on the answer.
        8. The ENTER key press will make the answer set in the rest of the views and will also
            unlock the answer field for the rest of the moderators.
         */
    }

    @Test
    void deleteQuestion() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. In the moderator view, pressing the delete button should
            make that question disappear from all windows.
         */
    }

    @Test
    void optionsClicked() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. In any of the views, pressing the Options button should result in a dropdown of the
            different hit boxes with the functionalities that they represent.
         */
    }

    @Test
    void optionsHidden() {
        /* Manual test plan to be carried out when testing if everything works correctly.
        Test plan for testing if loading data from server works:
        1. Launch the server
        2. Launch the client application twice.
        3. Create a room in one of the windows.
        4. Join that room in the other window as a student.
        5. Ask a question as a student.
        6. In the moderator view, pressing the delete button should
            make that question disappear from all windows.
         */
    }
}