package com.example.match.filtering.query.validators;

import com.example.match.filtering.query.Range;
import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class RangeValidator implements ConstraintValidator<InRange, Range> {

    private int low;
    private int high;


    @Override public void initialize(InRange constraintAnnotation) {
        this.low = constraintAnnotation.low();
        this.high = constraintAnnotation.high();
    }


    @Override public boolean isValid(Range range, ConstraintValidatorContext constraintValidatorContext) {
        return range.getLow() < range.getHigh() && range.getLow() >= low && range.getHigh() <= high;
    }
}
