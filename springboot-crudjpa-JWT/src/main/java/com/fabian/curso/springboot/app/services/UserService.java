package com.fabian.curso.springboot.app.services;

import com.fabian.curso.springboot.app.entities.User;

import java.util.List;

public interface UserService
{
    List<User> findAll();
    User save(User user);
    boolean existByNombre(String nombre);
}
