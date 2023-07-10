package com.rga.webflux.repository;

import com.rga.webflux.models.Persona;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.IncorrectResultSizeDataAccessException;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Repository
public class PostgresRepositoryImp implements IPostgresRepository
{
    @Autowired
    JdbcTemplate template;

    @Override
    public int save(Persona persona)
    {
        return template.update("INSERT INTO persona (nombre, apellido, direccion, telefono) VALUES(?,?,?,?)",
                persona.getNombre(), persona.getApellido(), persona.getDireccion(), persona.getTelefono());
    }

    @Override
    public Flux<Persona> findAll()
    {
        return Flux.fromIterable(template.query("SELECT * from persona", BeanPropertyRowMapper.newInstance(Persona.class)));
    }

    @Override
    public Mono<Persona> findById(int id)
    {
        try
        {
            Persona persona = template.queryForObject("SELECT * FROM persona WHERE id=?",
                    BeanPropertyRowMapper.newInstance(Persona.class), id);
            return Mono.just(persona);
        } catch (IncorrectResultSizeDataAccessException e)
        {
            return Mono.empty();
        }
    }

    @Override
    public int update(Persona persona)
    {
        return template.update("UPDATE persona SET nombre=?, apellido=?, direccion=?, telefono=? WHERE id=?",
                persona.getNombre(),
                persona.getApellido(),
                persona.getDireccion(),
                persona.getTelefono(),
                Integer.parseInt(persona.getId()));

    }
}
