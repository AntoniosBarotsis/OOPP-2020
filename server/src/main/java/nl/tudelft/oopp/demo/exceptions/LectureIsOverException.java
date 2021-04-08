package nl.tudelft.oopp.demo.exceptions;

public class LectureIsOverException extends RuntimeException {
    public LectureIsOverException(String message) {
        super(message);
    }
}
