package com.rga.webflux;

import com.rga.webflux.handlers.HandlerPersonas;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.reactive.function.server.RequestPredicates;
import org.springframework.web.reactive.function.server.RouterFunction;
import org.springframework.web.reactive.function.server.RouterFunctions;
import org.springframework.web.reactive.function.server.ServerResponse;

@Configuration
public class RouterFunctionConfig
{
    @Bean
    public RouterFunction<ServerResponse> routes(HandlerPersonas handlerPersonas)
    {
        return RouterFunctions.route(RequestPredicates.GET("/apiV2/listar"), handlerPersonas::listar)
                .andRoute(RequestPredicates.POST("apiV2/registrar"), handlerPersonas::registrar);
    }
}
