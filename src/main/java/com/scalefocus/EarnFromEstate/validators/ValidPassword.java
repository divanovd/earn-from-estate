package com.scalefocus.EarnFromEstate.validators;

import com.scalefocus.EarnFromEstate.validators.validators.PasswordValidator;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.*;

@Target(ElementType.FIELD)
@Constraint(validatedBy = PasswordValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ValidPassword {

    String message() default "Password do not match the given format.";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};
}
