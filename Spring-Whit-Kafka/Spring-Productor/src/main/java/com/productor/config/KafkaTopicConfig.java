package com.productor.config;

import org.apache.kafka.clients.admin.NewTopic;
import org.apache.kafka.common.config.TopicConfig;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.config.TopicBuilder;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class KafkaTopicConfig
{
    @Bean
    public NewTopic createTopic()
    {
        Map<String, String> configTopic = new HashMap<>();
        configTopic.put(TopicConfig.CLEANUP_POLICY_CONFIG, TopicConfig.CLEANUP_POLICY_DELETE); // delete (borra mensaje), compact (Mantiene el mas actualizado)
        configTopic.put(TopicConfig.RETENTION_MS_CONFIG, "86400000"); // Tiempo de retencion de mensajes -defecto -1 por lo que no los borra si no se configura el tiempo
        configTopic.put(TopicConfig.SEGMENT_BYTES_CONFIG, "1073741824"); // Tamaño maximo del segmento - defeto 1GB
        configTopic.put(TopicConfig.MAX_MESSAGE_BYTES_CONFIG, "1000012"); // Tamaño maximo de cada mensaje - defecto 1MB
        return TopicBuilder.name("Kafka")
                .partitions(2)
                .configs(configTopic)
                .build();
    }
}
