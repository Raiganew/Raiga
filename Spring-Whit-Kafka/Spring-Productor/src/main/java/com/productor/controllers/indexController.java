package com.productor.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class indexController
{
    @Autowired
    private KafkaTemplate<String, String> template;

    @GetMapping("/envioMensaje")
    public String sendMessage(@RequestBody String message)
    {
        template.send("Kafka", message); // Se especifia el topic al que va el mensaje y el mensaje como tal
        return message.concat(" OK");
    }
}
