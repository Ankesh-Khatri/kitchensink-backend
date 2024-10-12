package org.spring.as.quickstarts.kitchensink.exception;

import jakarta.validation.ConstraintViolationException;
import lombok.extern.log4j.Log4j2;
import org.spring.as.quickstarts.kitchensink.model.response.RestApiResponse;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.sql.SQLException;

@RestControllerAdvice
@Log4j2
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ConstraintViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public final ResponseEntity<RestApiResponse> handleConstraintViolationException(ConstraintViolationException exception) {
        logException(exception, ConstraintViolationException.class);
        var response = new RestApiResponse(exception.getLocalizedMessage(), null, false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(DataIntegrityViolationException.class)
    @ResponseStatus(HttpStatus.OK)
    public final ResponseEntity<?> dataIntegrityViolationException(SQLException exception) {
        logException(exception, SQLException.class);
        RestApiResponse response = new RestApiResponse(exception.getLocalizedMessage(), null, false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(ResourceNotFoundException.class)
    public final ResponseEntity<?> handleResourceNotFoundException(ResourceNotFoundException exception) {
        logException(exception, ResourceNotFoundException.class);
        RestApiResponse response = new RestApiResponse(exception.getLocalizedMessage(), null, false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    public final ResponseEntity<?> handleException(Exception exception) {
        logException(exception, Exception.class);
        RestApiResponse response = new RestApiResponse(exception.getLocalizedMessage(), null, false);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }

    private <T extends Exception> void logException(Exception ex, Class<T> clazz) {
        log.warn("Exception {} handled :: {}", clazz.getSimpleName(), ex.getMessage(), ex);
        if (ex.getCause() != null) {
            log.info("Cause handled :: {0}", ex.getCause());
        }
    }
}
