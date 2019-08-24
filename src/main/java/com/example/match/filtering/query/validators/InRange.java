package com.example.match.filtering.query.validators;

import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import javax.validation.Constraint;
import javax.validation.Payload;

@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Constraint(validatedBy = { RangeValidator.class })
public @interface InRange {
    String message() default "Value is out of range or not correct";

    Class<?>[] groups() default {};

    Class<? extends Payload>[] payload() default {};

    int low() default  Integer.MIN_VALUE;

    int high() default  Integer.MAX_VALUE;
}
