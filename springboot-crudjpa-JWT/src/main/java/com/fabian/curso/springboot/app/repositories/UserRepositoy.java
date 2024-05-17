package com.fabian.curso.springboot.app.repositories;

import com.fabian.curso.springboot.app.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepositoy extends CrudRepository<User, Long>
{
    boolean existsByNombre(String nombre);
    Optional<User> findByNombre(String nombre);
}
