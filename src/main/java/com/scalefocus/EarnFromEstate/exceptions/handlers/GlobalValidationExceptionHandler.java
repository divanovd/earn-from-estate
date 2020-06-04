package com.scalefocus.EarnFromEstate.exceptions.handlers;

import com.scalefocus.EarnFromEstate.exceptions.messages.ExceptionMessages;
import com.scalefocus.EarnFromEstate.exceptions.responses.GlobalExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Global handler to all validation errors within the application.
 */
@ControllerAdvice
public class GlobalValidationExceptionHandler {

    @ExceptionHandler(MethodArgumentNotValidException.class)
    protected ResponseEntity<GlobalExceptionResponse> handleValidationInvalid(final MethodArgumentNotValidException ex) {
        final GlobalExceptionResponse response = GlobalExceptionResponse.builder()
                .message(constructMessage(ex))
                .occurred(Instant.now())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

    /**
     * Constructs the error message.
     *
     * @param ex the exception.
     * @return the message.
     */
    private String constructMessage(final MethodArgumentNotValidException ex) {
        final String rawMessage = ex.getBindingResult().getFieldError().toString();
        final String message = rawMessage.substring(0, rawMessage.indexOf(':')) + " !";
        return ExceptionMessages.INVALID_VALIDATION_MESSAGE + message;
    }
}
