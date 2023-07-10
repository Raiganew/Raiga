package com.rga.webflux.controllers;

import com.rga.webflux.models.Persona;
import com.rga.webflux.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MainController
{
    @Autowired
    private MainService service;

    @GetMapping("/personas")
    public Flux<Persona> findAll()
    {
        return service.findAll();
    }

    @PostMapping("/crear")
    public Mono<Persona> crear(@RequestBody Persona persona)
    {
        return service.create(Mono.just(persona));
    }

    @PostMapping("/crearV2")
    public Flux<Persona> crearVarias(@RequestBody List<Persona> personas)
    {
        return service.createLista(personas);
    }

    @GetMapping("/find/{id}")
    public Mono<Persona> findById(@PathVariable String id)
    {
        return service.findById(id);
    }

    @PutMapping("/update")
    public Mono<ResponseEntity<Persona>> update(@RequestBody Persona persona)
    {
        return service.update(Mono.just(persona));
    }

    @PutMapping("/updateV2")
    public Mono<ResponseEntity<List<Persona>>> update(@RequestBody List<Persona> personas)
    {
        return service.updateV2(Flux.fromIterable(personas)).flatMap(m ->
                {
                    if (m.isEmpty())
                    {
                        return Mono.empty();
                    }
                    return Mono.just(new ResponseEntity<>(m, null, HttpStatus.CREATED));
                })
                .switchIfEmpty(Mono.just(new ResponseEntity<>(null, null, HttpStatus.NO_CONTENT)));
    }
}
