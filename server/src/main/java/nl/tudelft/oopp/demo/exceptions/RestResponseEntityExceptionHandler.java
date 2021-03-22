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
    @ExceptionHandler(value = {InvalidIdException.class, InvalidPasswordException.class,
        UnauthorizedException.class})
    protected ResponseEntity<Object> handleConflict(
        RuntimeException ex, WebRequest request) {

        return handleExceptionInternal(ex, "[" + ex.getClass().getName() + "]: "
                + ex.getLocalizedMessage(), new HttpHeaders(), 
                HttpStatus.EXPECTATION_FAILED, request);
    }

}