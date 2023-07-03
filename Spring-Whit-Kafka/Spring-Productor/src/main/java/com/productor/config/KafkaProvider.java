package com.productor.config;

import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.core.ProducerFactory;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaProvider
{
    @Value("${spring.kafka.bootstrapServers}")
    private String serverKafka;

    public Map<String, Object> producerConfig()
    {
        Map<String, Object> properties = new HashMap<>();
        properties.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, serverKafka); // agregamos la ip donde esta kafka
        properties.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class); // Definimos la clase encargada de serailizar la llave, en este caso se usa la de StringSerializer de kafka
        properties.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class);// Lo mismo pero con el valor del mapa
        return properties;
    }

    @Bean
    public ProducerFactory<String, String> providerFactory()
    {
        return new DefaultKafkaProducerFactory<>(producerConfig()); // Creamos el bean con las propiedades que previamente definimos
    }

    @Bean
    @Primary
    public KafkaTemplate<String, String> kafkaTemplate(ProducerFactory<String, String> factory)
    {
        return new KafkaTemplate<>(factory);// Con este Kafka Template ya podemos enviar mensajes al servidor de Kafka
    }
}
