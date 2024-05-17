package com.fabian.curso.springboot.app.validation;

import com.fabian.curso.springboot.app.services.UserService;
import jakarta.validation.ConstraintValidator;
import jakarta.validation.ConstraintValidatorContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ExistByUserNameValidation implements ConstraintValidator<ExistByUserName,String>
{
    @Autowired
    private UserService service;
    @Override
    public boolean isValid(String nombre, ConstraintValidatorContext context)
    {
        if (service != null)
        {
              return !service.existByNombre(nombre);
        }
        return true;
    }
}
