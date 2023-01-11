package com.kondoumh.kafkaconsumerexample;

import java.util.HashMap;
import java.util.Map;

import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.ConsumerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.core.KafkaOperations;
import org.springframework.kafka.support.serializer.JsonDeserializer;

@EnableKafka
@Configuration
public class KafkaListenerConfig {

    @Bean
    public ConsumerFactory<String, ExampleEvent> consumerFactory() {
        Map<String, Object> configs = new HashMap<>();
        configs.put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, "localhost:9092");
        configs.put(ConsumerConfig.GROUP_ID_CONFIG, "example-group");
        JsonDeserializer<ExampleEvent> deserializer = new JsonDeserializer<>(ExampleEvent.class);
        return new DefaultKafkaConsumerFactory<>(configs, new StringDeserializer(), deserializer);
    }

    public ConcurrentKafkaListenerContainerFactory<String, ExampleEvent> kafkaListenerContainerFactory(KafkaOperations<String, ExampleEvent> operations) {
        ConcurrentKafkaListenerContainerFactory<String, ExampleEvent> factory = new ConcurrentKafkaListenerContainerFactory<>();
        return factory;
    }
}
