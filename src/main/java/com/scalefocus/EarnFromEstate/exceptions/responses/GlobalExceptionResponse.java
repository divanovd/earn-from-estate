package com.scalefocus.EarnFromEstate.exceptions.responses;

import lombok.Builder;
import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.io.Serializable;
import java.time.Instant;

@Getter
@Builder
public class GlobalExceptionResponse implements Serializable {
    private static final long serialVersionUID = -5576942961811321192L;

    private final HttpStatus status;
    private final String message;
    private final Instant occurred;
}
