package com.kondoumh.kafkaproducerexample.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

import com.kondoumh.kafkaproducerexample.listener.model.Message;

@Component
public class ExampleConsumer {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleConsumer.class);

    @KafkaListener(topics = "test-topic")
    public void listen(
            @Payload Message message,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.GROUP_ID) String groupId,
            @Header(KafkaHeaders.OFFSET) Long offset,
            @Header("X-CUSTOM-HEADER") String customHeader) {
        LOGGER.info("Received: {}, {}, {}, {}, {}, {}", message, key, partition, groupId, offset, customHeader);
    }
}
