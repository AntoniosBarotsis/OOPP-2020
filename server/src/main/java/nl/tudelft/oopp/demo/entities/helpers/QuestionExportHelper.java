package nl.tudelft.oopp.demo.entities.helpers;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import java.util.Date;
import lombok.Data;
import nl.tudelft.oopp.demo.entities.serializers.QuestionExportSerializer;

/**
 * A smaller version of the Question class used for exporting.
 */
@Data
@JsonSerialize(using = QuestionExportSerializer.class)
public class QuestionExportHelper {
    private String text;
    private String answer;
    private Date timeCreated;

    /**
     * Instantiates a new Question export helper.
     *
     * @param text        the text
     * @param answer      the answer
     * @param timeCreated the time created
     */
    public QuestionExportHelper(String text, String answer, Date timeCreated) {
        this.text = text;
        this.answer = answer;
        this.timeCreated = timeCreated;
    }
}
