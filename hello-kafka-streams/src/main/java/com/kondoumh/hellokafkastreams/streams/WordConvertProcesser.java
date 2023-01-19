package com.kondoumh.hellokafkastreams.streams;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.Produced;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordConvertProcesser {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordConvertProcesser.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {
        KStream<String, String> messageStream = streamsBuilder
            .stream("stream-input-topic", Consumed.with(STRING_SERDE, STRING_SERDE));

        messageStream
            .filter((k, v) -> v.startsWith("foo_"))
            .mapValues((k, v) -> v.toUpperCase())
            .peek((k, v) -> LOGGER.info("key: {} word: {}", k, v))
            .to("stream-output-topic", Produced.with(STRING_SERDE, STRING_SERDE));
    }
}
