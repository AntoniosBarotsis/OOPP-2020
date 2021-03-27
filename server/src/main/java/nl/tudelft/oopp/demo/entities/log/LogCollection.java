package nl.tudelft.oopp.demo.entities.log;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * The type Log collection. A collection of all types of LogEntries.
 */
@Data
@AllArgsConstructor
public class LogCollection {
    private List<LogBan> bans;
    private List<LogJoin> joins;
    private List<LogQuestion> questions;

    /**
     * Instantiates a new Log collection.
     */
    public LogCollection() {
        this.bans = new ArrayList<>();
        this.joins = new ArrayList<>();
        this.questions = new ArrayList<>();
    }
}
