package com.kondoumh.kafkaconsumerexample;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExampleEvent {
    @JsonProperty("event_id")
    Long eventId;
    @JsonProperty("event_name")
    String eventName;
}
