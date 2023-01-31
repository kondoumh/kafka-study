package com.kondoumh.kafkaproducerexample.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kondoumh.kafkaproducerexample.controller.model.SendMessageRequest;
import com.kondoumh.kafkaproducerexample.producer.ExampleProducer;

@RestController
@RequestMapping("/message")
public class SendMessageController {

  private static final Logger LOGGER = LoggerFactory.getLogger(SendMessageController.class);
  ExampleProducer exampleProducer;

  public SendMessageController(ExampleProducer exampleProducer) {
    this.exampleProducer = exampleProducer;
  }

  @PostMapping()
  public ResponseEntity<Void> sendMessage(@RequestBody SendMessageRequest request) {
    LOGGER.info("request {}", request);
    exampleProducer.produce(request.getTitle(), request.getBody());
    return new ResponseEntity<>(HttpStatus.CREATED);
  }
}
