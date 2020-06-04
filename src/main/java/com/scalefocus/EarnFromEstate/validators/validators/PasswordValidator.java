package com.scalefocus.EarnFromEstate.validators.validators;

import com.scalefocus.EarnFromEstate.validators.ValidPassword;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to verify the user password by a given regex.
 */
public class PasswordValidator implements ConstraintValidator<ValidPassword, String> {

    private final static String PASSWORD_PATTERN = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=\\S+$).{8,}$";

    @Override
    public void initialize(final ValidPassword constraintAnnotation) {

    }

    /**
     * Validates the password of the UserDto based on the regex.
     *
     * @param password the user password.
     * @return if the password is valid.
     */
    @Override
    public boolean isValid(final String password, final ConstraintValidatorContext context) {
        final Pattern pattern = Pattern.compile(PASSWORD_PATTERN);
        final Matcher matcher = pattern.matcher(password);

        return matcher.matches();
    }

}
