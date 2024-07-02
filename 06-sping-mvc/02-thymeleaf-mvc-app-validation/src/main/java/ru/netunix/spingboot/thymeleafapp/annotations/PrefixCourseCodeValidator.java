package ru.netunix.spingboot.thymeleafapp.annotations;

import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;

public class PrefixCourseCodeValidator implements ConstraintValidator<PrefixCourseCode, String> {
    private String coursePrefix;

    @Override
    public void initialize(PrefixCourseCode thePrefixCourseCode) {
        coursePrefix = thePrefixCourseCode.value();
    }

    @Override
    public boolean isValid(String theCode, ConstraintValidatorContext constraintValidatorContext) {
      return theCode == null || theCode.startsWith(coursePrefix);
    }
}
