package com.fabian.curso.springboot.app.repositories;

import com.fabian.curso.springboot.app.entities.Role;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface RoleRepository extends CrudRepository<Role, Long>
{

    Optional<Role> findByNombre(String name);
}
