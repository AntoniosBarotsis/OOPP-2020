package nl.tudelft.oopp.demo.repositories;

import java.util.List;
import java.util.Set;
import javax.transaction.Transactional;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface RoomRepository extends JpaRepository<Room, Long> {
    @Transactional
    @Query(value = "SELECT normal_password FROM rooms WHERE id = ?1", nativeQuery = true)
    String getPublicPassword(long roomId);

    @Transactional
    @Query(value = "SELECT elevated_password FROM rooms WHERE id = ?1", nativeQuery = true)
    String getPrivatePassword(long roomId);

    @Transactional
    @Query(value = "SELECT q FROM Question q, Room r where r.id = ?1 AND q.id = r.id")
    Set<Question> findAllQuestions(long roomId);
}
