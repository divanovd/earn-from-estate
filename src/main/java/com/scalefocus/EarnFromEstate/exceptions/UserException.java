package com.scalefocus.EarnFromEstate.exceptions;

/**
 * Occurs on repository level whenever there is a problem with persisting an user.
 */
public class UserException extends RuntimeException {

    private static final long serialVersionUID = 3698074121285147597L;

    public UserException(final String message) {
        super(message);
    }
}
