package nl.tudelft.oopp.demo.services;

import java.util.List;
import java.util.Set;
import nl.tudelft.oopp.demo.entities.Room;
import nl.tudelft.oopp.demo.entities.questions.Question;
import nl.tudelft.oopp.demo.repositories.RoomRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RoomService {
    @Autowired
    private final RoomRepository roomRepository;

    public RoomService(RoomRepository roomRepository) {
        this.roomRepository = roomRepository;
    }

    public List<Room> findAll() {
        return roomRepository.findAll();
    }

    public Room getOne(long id) {
        return roomRepository.getOne(id);
    }

    public String getPublicPassword(long roomId) {
        return roomRepository.getPublicPassword(roomId);
    }

    public String getPrivatePassword(long roomId) {
        // Later perform some sort of check here to see if the user has permission
        return roomRepository.getPrivatePassword(roomId);
    }

    public Set<Question> findAllQuestions(long roomId) {
        return roomRepository.findAllQuestions(roomId);
    }
}
