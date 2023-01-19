package com.kondoumh.hellokafkastreams.streams;

import java.util.Arrays;

import org.apache.kafka.common.serialization.Serde;
import org.apache.kafka.common.serialization.Serdes;
import org.apache.kafka.streams.KeyValue;
import org.apache.kafka.streams.StreamsBuilder;
import org.apache.kafka.streams.kstream.Consumed;
import org.apache.kafka.streams.kstream.Grouped;
import org.apache.kafka.streams.kstream.KStream;
import org.apache.kafka.streams.kstream.KTable;
import org.apache.kafka.streams.kstream.Produced;
import org.apache.kafka.streams.kstream.ValueMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class WordCountProcessor {

    private static final Logger LOGGER = LoggerFactory.getLogger(WordCountProcessor.class);

    private static final Serde<String> STRING_SERDE = Serdes.String();
    private static final Serde<Long> LONG_SERDE = Serdes.Long();

    @Autowired
    void buildPipeline(StreamsBuilder streamsBuilder) {

        KStream<String, String> messageStream = streamsBuilder
        .stream("word-input-topic", Consumed.with(STRING_SERDE, STRING_SERDE));

        KTable<String, Long> wordCounts = messageStream
            .mapValues((ValueMapper<String, String>) String::toLowerCase)
            .flatMapValues(value -> Arrays.asList(value.split("\\W+")))
            .peek((key, word) -> LOGGER.info("key: {} word: {}", key, word))
            .groupBy((key, word) -> word, Grouped.with(STRING_SERDE, STRING_SERDE))
            .count();

        wordCounts.toStream()
            .peek((word, count) -> LOGGER.info("word: {} count: {}", word, count))
            .map((word, count) -> new KeyValue<>(word, count.toString()))
            .to("word-output-topic", Produced.with(STRING_SERDE, STRING_SERDE));
    }
}
