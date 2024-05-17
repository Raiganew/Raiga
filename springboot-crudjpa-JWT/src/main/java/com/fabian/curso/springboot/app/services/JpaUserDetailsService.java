package com.fabian.curso.springboot.app.services;

import com.fabian.curso.springboot.app.entities.User;
import com.fabian.curso.springboot.app.repositories.UserRepositoy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class JpaUserDetailsService implements UserDetailsService
{
    @Autowired
    private UserRepositoy userRepositoy;
    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException
    {
        Optional<User> optinalUser = userRepositoy.findByNombre(username);
        if(optinalUser.isEmpty()){
            throw new UsernameNotFoundException(String.format("Usuario %s no existe en base de datos",username));
        }

        User user = optinalUser.orElseThrow();
        List<GrantedAuthority> authorities = user.getRoles().stream()
                .map(rol ->new SimpleGrantedAuthority(rol.getNombre()))
                .collect(Collectors.toList());

        return new org.springframework.security.core.userdetails.User(user.getNombre(),user.getPassword(),
                user.isEnable(),
                true,true,true,
                authorities);
    }
}
