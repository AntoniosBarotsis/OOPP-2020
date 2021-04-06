package nl.tudelft.oopp.demo.exceptions;

public class InvalidPollStatusException extends RuntimeException {
    public InvalidPollStatusException(String message) {
        super(message);
    }
}
