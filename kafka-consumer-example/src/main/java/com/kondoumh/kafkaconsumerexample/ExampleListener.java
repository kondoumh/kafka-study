package com.kondoumh.kafkaconsumerexample;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.support.KafkaHeaders;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.stereotype.Component;

@Component
public class ExampleListener {

    private static final Logger LOGGER = LoggerFactory.getLogger(ExampleListener.class);

    @KafkaListener(topics = "example-topic-01")
    public void listen(
            @Payload ExampleEvent event,
            @Header(KafkaHeaders.RECEIVED_PARTITION) Integer partition,
            @Header(KafkaHeaders.RECEIVED_KEY) String key,
            @Header(KafkaHeaders.GROUP_ID) String groupId,
            @Header(KafkaHeaders.OFFSET) Long offset) {
        LOGGER.info("Received: {}, {}, {}, {}, {}", partition, groupId, offset, key, event);
    }
}
