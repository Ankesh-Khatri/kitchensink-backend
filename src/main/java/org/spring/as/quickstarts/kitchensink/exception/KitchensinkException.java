package org.spring.as.quickstarts.kitchensink.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

@Getter
public class KitchensinkException extends RuntimeException {

    private final Integer statusCode;

    public KitchensinkException(String message) {
        super(message);
        this.statusCode = HttpStatus.INTERNAL_SERVER_ERROR.value();
    }

    public KitchensinkException(String message, Integer statusCode) {
        super(message);
        this.statusCode = statusCode;
    }
}
