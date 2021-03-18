package nl.tudelft.oopp.demo.repositories;

import nl.tudelft.oopp.demo.entities.log.LogEntry;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LogEntryRepository extends JpaRepository<LogEntry, Long> {
}
