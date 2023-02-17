package com.kondoumh.kafkaproducerexample.producer;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

import org.apache.kafka.clients.producer.ProducerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.kafka.support.SendResult;
import org.springframework.stereotype.Component;

import com.kondoumh.kafkaproducerexample.producer.model.Message;

@Component
public class ExampleProducer {
  private static final Logger LOGGER = LoggerFactory.getLogger(ExampleProducer.class);

  private KafkaTemplate<String, Message> kafkaTemplate;
  private final String TOPIC = "test-topic";
  private static String PARTITION_KEY = "partition-key";

  public ExampleProducer(KafkaTemplate<String, Message> kafkaTemplate) {
    this.kafkaTemplate = kafkaTemplate;
  }

  public void produce(String title, String body) {
    var message = new Message(1, title, body);
    var producerRecord = new ProducerRecord<String, Message>(TOPIC, message);
    // Add custom header
    producerRecord.headers().add("foo", "bar".getBytes());
    kafkaTemplate.send(producerRecord);

    // CompletableFuture<SendResult<String, Message>> future = kafkaTemplate.send(TOPIC, PARTITION_KEY, message);
    // try {
    //   var result = future.get();
    //   LOGGER.info("result {}", result.getRecordMetadata());
    // } catch (InterruptedException e) {
    //   e.printStackTrace();
    // } catch (ExecutionException e) {
    //   e.printStackTrace();
    // }
  }
}
