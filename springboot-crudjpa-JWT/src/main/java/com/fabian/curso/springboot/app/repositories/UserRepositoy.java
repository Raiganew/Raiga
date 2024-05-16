package com.fabian.curso.springboot.app.repositories;

import com.fabian.curso.springboot.app.entities.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepositoy extends CrudRepository<User, Long>
{
    boolean existsByNombre(String nombre);
}
