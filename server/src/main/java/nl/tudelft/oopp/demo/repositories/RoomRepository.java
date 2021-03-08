package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.Room;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {

    /**
     * Gets public password.
     *
     * @param roomId the room id
     * @return the public password
     */
    @Transactional
    @Query(value = "SELECT r.normalPassword FROM Room r WHERE r.id = ?1")
    String getPublicPassword(long roomId);

    /**
     * Gets private password.
     *
     * @param roomId the room id
     * @return the private password
     */
    @Transactional
    @Query(value = "SELECT r.elevatedPassword FROM Room r WHERE r.id = ?1")
    String getPrivatePassword(long roomId);

    /**
     * Creates a new room
     *
     * @param roomId the room id
     * @param title the title of the room
     * @param repeatingLecture whether the room will be used again later
     */
    // TODO Check annotations
    // TODO Add scheduling
    @Transactional
    @Query(value = "INSERT INTO Room (id, title, repeatingLecture, tooFast, tooSlow) VALUES (?1, ?2, ?3, 0, 0)")
    void createRoom(long roomId, String title, boolean repeatingLecture);
}
