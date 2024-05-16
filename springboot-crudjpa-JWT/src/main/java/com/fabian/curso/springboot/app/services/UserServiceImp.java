package com.fabian.curso.springboot.app.services;

import com.fabian.curso.springboot.app.entities.Role;
import com.fabian.curso.springboot.app.entities.User;
import com.fabian.curso.springboot.app.repositories.RoleRepository;
import com.fabian.curso.springboot.app.repositories.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImp implements UserService
{
    @Autowired
    private UserRepositoy userRepositoy;

    @Autowired
    private RoleRepository roleRepository;
    @Autowired
    private PasswordEncoder passwordEncoder;
    @Override
    @Transactional(readOnly = true)
    public List<User> findAll()
    {
        return (List<User>) userRepositoy.findAll();
    }

    @Override
    @Transactional
    public User save(User user)
    {
        Optional<Role> optionalRoleUser = roleRepository.findByNombre("ROLE_USER");
        List<Role> roles = new ArrayList<>();
        optionalRoleUser.ifPresent(roles::add);
        if (user.isAdmin()){
            Optional<Role> optionalRoleAdmin = roleRepository.findByNombre("ROLE_ADMIN");
            optionalRoleAdmin.ifPresent(roles::add);
        }
        user.setRoles(roles);

        //passwordEncoder se crea el bean en una clase config para inyectarla
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userRepositoy.save(user);
    }

    @Override
    public boolean existByNombre(String nombre)
    {
        boolean response =userRepositoy.existsByNombre(nombre);
        return response;
    }
}
