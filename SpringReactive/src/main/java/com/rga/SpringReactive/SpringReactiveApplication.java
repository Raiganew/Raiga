package com.rga.SpringReactive;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import reactor.core.publisher.Flux;

@SpringBootApplication
public class SpringReactiveApplication implements CommandLineRunner
{
    private static final Logger log = LoggerFactory.getLogger(SpringReactiveApplication.class);

    public static void main(String[] args)
    {
        SpringApplication.run(SpringReactiveApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception
    {
        Flux<String> nombres = Flux.just("Prinz", "Ulrich", "Simakaze", "Adalbert", "", "Prueba")
                .map(String::toLowerCase)
                .doOnNext(n ->
                {
                    if (n.isEmpty())
                    {
                        throw new RuntimeException("Nombre Vacio");
                    }
                    System.out.println(n);
                })
                .map(String::toUpperCase);
        nombres.subscribe(log::info, error -> log.error(error.getMessage()));
    }
}
