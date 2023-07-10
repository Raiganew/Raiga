package com.rga.webflux.services;

import com.rga.webflux.models.Persona;
import com.rga.webflux.repository.PostgresRepositoryImp;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@Service
public class MainService
{
    private static final Logger log = LoggerFactory.getLogger(MainService.class);
    @Autowired
    private PostgresRepositoryImp db;

    public Flux<Persona> findAll()
    {
        return db.findAll();
    }

    public Mono<Persona> findById(String id)
    {
        try
        {
            return db.findById(Integer.parseInt(id));
        } catch (NumberFormatException e)
        {
            log.error("Id ".concat(id).concat(" No valido ").concat(e.getMessage()));
            return Mono.empty();
        }
    }

    public Mono<Persona> create(Mono<Persona> persona)
    {
        return persona.flatMap(p ->
        {
            if (p.getDireccion().isEmpty())
            {
                return Mono.empty();
            }
            return Mono.just(p);
        }).doOnNext(p -> db.save(p)).switchIfEmpty(Mono.empty());
    }

    public Flux<Persona> createLista(List<Persona> personas)
    {
        Flux<Persona> personasFlux = Flux.fromIterable(personas);
        return personasFlux.flatMap(p ->
        {
            if (p.getDireccion().isEmpty())
            {
                log.error(p.getNombre().concat(" ")
                        .concat(p.getApellido()).concat(" ")
                        .concat(" Sin Direccion"));
                return Mono.empty();
            }
            return Mono.just(p);
        }).doOnNext(p ->
                {
                    log.info(p.toString());
                    db.save(p);
                }
        ).switchIfEmpty(Mono.empty());
    }

    public Mono<ResponseEntity<Persona>> update(Mono<Persona> persona)
    {
        return persona.flatMap(p ->
        {
            log.info("UPDATE:".concat(p.toString()));
            if (p.getId() == null || p.getId().isEmpty())
            {
                log.error(p.getNombre().concat(" ")
                        .concat(p.getApellido()).concat(" ")
                        .concat(" Sin Id"));
                return Mono.empty();
            }
            if (db.update(p) == 1)
            {
                log.info("UPDATE SUCCES");
            }
            return Mono.just(new ResponseEntity<Persona>(p, null, HttpStatus.OK));
        }).switchIfEmpty(Mono.just(new ResponseEntity<Persona>(null, null, HttpStatus.NOT_MODIFIED)));
    }

    public Mono<List<Persona>> updateV2(Flux<Persona> personas)
    {
        return personas.flatMap(p ->
        {
            log.info("UPDATE:".concat(p.toString()));
            if (p.getId() == null || p.getId().isEmpty() || db.update(p) != 1)
            {
                log.error(p.getNombre().concat(" ")
                        .concat(p.getApellido()).concat(" ")
                        .concat(" UPDATE FAIL"));
                return Mono.empty();
            }
            log.info("UPDATE SUCCES");
            return Mono.just(p);
        }).collectList();
    }
}
