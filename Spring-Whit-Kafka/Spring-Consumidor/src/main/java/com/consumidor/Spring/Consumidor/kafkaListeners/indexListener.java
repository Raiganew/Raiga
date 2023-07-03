package com.consumidor.Spring.Consumidor.kafkaListeners;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
public class indexListener
{
    private static Logger log = LoggerFactory.getLogger(indexListener.class);

    @KafkaListener(topics = {"Kafka"}, groupId = "primero")
    // Se definene los topics a los que va a estar atento y ademas el group ID que es el que nos ayuda a crear un grupo de listener los cuales escuchan los mismos topics
    public void primerListener(String message)
    {
        log.info("Mensaje obtenido:" + message);
    }
}
