package ru.netunix.spingboot.thymeleafapp.annotations;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = PrefixCourseCodeValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ElementType.FIELD, ElementType.METHOD})
public @interface PrefixCourseCode {
    //define default prefix of code
public String value() default "Spring";
    //define default error message
public String message() default "must start with Spring";
    //define default groups
    public Class<?>[] groups() default {};

    //define default payloads
    public Class<? extends Payload>[] payload() default {};
}
