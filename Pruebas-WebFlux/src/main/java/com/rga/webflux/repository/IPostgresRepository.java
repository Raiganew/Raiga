package com.rga.webflux.repository;

import com.rga.webflux.models.Persona;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

public interface IPostgresRepository
{
    public int save(Persona persona);

    public Flux<Persona> findAll();

    public Mono<Persona> findById(int id);

    public int update(Persona persona);
}
