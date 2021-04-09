package nl.tudelft.oopp.demo.exceptions;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class RestResponseEntityExceptionHandler extends ResponseEntityExceptionHandler {
    @ExceptionHandler(value = {
        InvalidIdException.class,
        InvalidPasswordException.class,
        InvalidPollStatusException.class,
        LectureIsOverException.class,
        UnauthorizedException.class
    })
    protected ResponseEntity<Object> handleConflict(
        RuntimeException ex, WebRequest request) {
        HttpStatus status = null;

        String name = ex.getClass()
            .getName()
            .replace("nl.tudelft.oopp.demo.exceptions.", "");

        switch (name) {
            case "InvalidIdException":
            case "InvalidPollStatusException":
                status = HttpStatus.NOT_FOUND;
                break;
            case "InvalidPasswordException":
            case "UnauthorizedException":
                status = HttpStatus.UNAUTHORIZED;
                break;
            case "LectureIsOverException":
                status = HttpStatus.EXPECTATION_FAILED;
                break;
            default:
                status = HttpStatus.BAD_REQUEST;
        }

        return handleExceptionInternal(ex, "[" + ex.getClass().getName() + "]: "
                + ex.getLocalizedMessage(), new HttpHeaders(), status, request);
    }

}