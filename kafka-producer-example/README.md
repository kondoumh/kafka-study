# producer-example

A sample producer that sends JSON-formatted messages to a topic.

Posting a request in JSON format to the REST API endpoint /message will send the requested message to the topic `test-topic` .

Boot command.

```shell
./gradlew bootRun
```

Make a request with a title and body in JSON.

```shell
curl -X POST -H "Content-Type: application/json" http://localhost:3001/message -d '{"title":"hello","body":"world"}'
```

Receive the message on the consumer.

```shell
$ kafka-console-consumer \
  --bootstrap-server localhost:9092 \
  --topic test-topic \
  --from-beginning \
  --property print.headers=true

foo:bar,__TypeId__:com.kondoumh.kafkaproducerexample.producer.model.Message	{"message_id":1,"title":"hello","body":"world"}
```