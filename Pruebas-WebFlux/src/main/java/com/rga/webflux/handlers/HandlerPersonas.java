package com.rga.webflux.handlers;

import com.rga.webflux.models.MessageJon;
import com.rga.webflux.models.Persona;
import com.rga.webflux.services.MainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.server.ServerRequest;
import org.springframework.web.reactive.function.server.ServerResponse;
import reactor.core.publisher.Mono;

import java.util.List;

@Component
public class HandlerPersonas
{
    @Autowired
    MainService service;

    public Mono<ServerResponse> listar(ServerRequest request)
    {
        return ServerResponse.ok()
                .contentType(MediaType.APPLICATION_JSON)
                .body(service.findAll(), Persona.class);
    }

    public Mono<ServerResponse> registrar(ServerRequest request)
    {
        Mono<MessageJon> personaMono = request.bodyToMono(MessageJon.class);
        personaMono.flatMap(p ->
                {
                    System.out.println(p.getMessage());
                    return Mono.just(p);
                })
                .doOnNext(p -> p.toString())
                .onErrorResume(throwable ->
                {
                    // Manejo del error
                    System.err.println("Error: " + throwable.getMessage());
                    // Devuelve un valor alternativo o un flujo alternativo en caso de error
                    return Mono.empty();
                })
                .flatMap(p ->
                {
                    // Realizar acciones adicionales con el objeto Persona
                    // ...
                    return Mono.just(p);
                })
                .doOnSuccess(p ->
                {
                    // Acciones después de que el flujo se haya completado con éxito
                    // ...
                })
                .doOnError(throwable ->
                {
                    // Acciones adicionales en caso de error
                    // ...
                })
                .subscribe();
        return ServerResponse.noContent().build();
    }
}
