package com.fabian.curso.springboot.app.validation;

import jakarta.validation.Constraint;
import jakarta.validation.Payload;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
@Constraint(validatedBy = ExistByUserNameValidation.class)
@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface ExistByUserName
{
    String message() default "ya existe en la base de datos!";

    Class<?>[] groups() default { };

    Class<? extends Payload>[] payload() default { };
}
