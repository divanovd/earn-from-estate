package com.scalefocus.EarnFromEstate.exceptions.handlers;

import com.scalefocus.EarnFromEstate.controllers.UserController;
import com.scalefocus.EarnFromEstate.exceptions.UserException;
import com.scalefocus.EarnFromEstate.exceptions.responses.GlobalExceptionResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.Instant;

/**
 * Used to handle all of the exceptions around operations with the user.
 */
@ControllerAdvice(assignableTypes = UserController.class)
public class UserExceptionHandler {

    @ExceptionHandler(value = UserException.class)
    protected ResponseEntity<GlobalExceptionResponse> matchingPasswordHandler(final UserException ex) {
        final GlobalExceptionResponse response = GlobalExceptionResponse.builder()
                .message(ex.getMessage())
                .occurred(Instant.now())
                .status(HttpStatus.BAD_REQUEST)
                .build();

        return new ResponseEntity<>(response, response.getStatus());
    }

}
