package com.fabian.curso.springboot.app.controllers;

import com.fabian.curso.springboot.app.entities.User;
import com.fabian.curso.springboot.app.services.UserService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/users")
@CrossOrigin(originPatterns = "*")
public class UserController
{
    @Autowired
    private UserService service;

    @GetMapping
    private List<User> getAll()
    {
        return service.findAll();
    }

    @PostMapping
    private ResponseEntity<?> save(@Valid @RequestBody User user, BindingResult result)
    {
        if (result.hasFieldErrors())
        {
            return validation(result);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(user));
    }

    @PostMapping("/register")
    private ResponseEntity<?> register(@Valid @RequestBody User user, BindingResult result)
    {
        user.setAdmin(false);
        return save(user,result);
    }

    private ResponseEntity<?> validation(BindingResult result)
    {
        Map<String, String> errors = new HashMap<>();
        result.getFieldErrors().forEach(e ->
        {
            errors.put(e.getField(), "El campo " + e.getField() + " " + e.getDefaultMessage());
        });
        return ResponseEntity.badRequest().body(errors);
    }
}
