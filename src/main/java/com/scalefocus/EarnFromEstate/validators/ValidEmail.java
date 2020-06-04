package com.scalefocus.EarnFromEstate.validators;

import com.scalefocus.EarnFromEstate.validators.validators.EmailValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = EmailValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidEmail {
    String message() default "Invalid email.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
