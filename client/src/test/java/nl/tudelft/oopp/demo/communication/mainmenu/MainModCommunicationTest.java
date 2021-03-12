package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import org.junit.jupiter.api.Test;

class MainModCommunicationTest {

    @Test
    void getStudentPassword() {
        assertNotNull(MainModCommunication.getStudentPassword(1));
    }

    @Test
    void getAdminPassword() {
        assertNotNull(MainModCommunication.getAdminPassword(1));
    }
}