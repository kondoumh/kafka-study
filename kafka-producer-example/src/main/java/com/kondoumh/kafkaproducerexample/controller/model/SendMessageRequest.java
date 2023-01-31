package com.kondoumh.kafkaproducerexample.controller.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class SendMessageRequest {
  @JsonProperty("title")
  String title;
  @JsonProperty("body")
  String body;
}
