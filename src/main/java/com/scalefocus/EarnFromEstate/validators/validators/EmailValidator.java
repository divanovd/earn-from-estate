package com.scalefocus.EarnFromEstate.validators.validators;

import com.scalefocus.EarnFromEstate.validators.ValidEmail;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Used to verify an email by a given pattern.
 */
public class EmailValidator implements ConstraintValidator<ValidEmail, String> {

    private static final String EMAIL_PATTERN = "^[_A-Za-z0-9-+]+(.[_A-Za-z0-9-]+)*@"
            + "[A-Za-z0-9-]+(.[A-Za-z0-9]+)*(.[A-Za-z]{2,})$";

    @Override
    public void initialize(final ValidEmail constraintAnnotation) {
    }

    /**
     * Used to validate the email by a given regex pattern.
     *
     * @param email the email to be validated.
     * @return is email vlaid or not.
     */
    @Override
    public boolean isValid(final String email, final ConstraintValidatorContext context) {
        final Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        final Matcher matcher = pattern.matcher(email);
        return matcher.matches();
    }

}
