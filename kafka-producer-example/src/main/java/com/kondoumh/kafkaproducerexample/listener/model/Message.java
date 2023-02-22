package com.kondoumh.kafkaproducerexample.listener.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Message {
  @JsonProperty("message_id")
  Integer messageId;
  @JsonProperty("title")
  String title;
  @JsonProperty("body")
  String body;
}
