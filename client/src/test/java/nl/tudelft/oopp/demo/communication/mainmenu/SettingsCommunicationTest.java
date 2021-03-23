package nl.tudelft.oopp.demo.communication.mainmenu;

import static org.junit.jupiter.api.Assertions.assertNotNull;

import nl.tudelft.oopp.demo.data.RoomConfig;
import org.junit.jupiter.api.Test;

class SettingsCommunicationTest {

    @Test
    void getStudentPassword() {
        assertNotNull(SettingsCommunication.getStudentPassword(1));
    }

    @Test
    void getAdminPassword() {
        assertNotNull(SettingsCommunication.getAdminPassword(1));
    }

    @Test
    void saveSettings() {
        assertNotNull(SettingsCommunication.saveSettings(1, 1, new RoomConfig(1,2,3,4)));
    }
}