# spring-cloud-stream-sample

## Without schema registry.
To use producers and consumers example without a schema registry, just comment or remove

- from producer-properties
```yml
  key.serializer: org.apache.kafka.common.serialization.StringSerializer
  value.serializer: io.confluent.kafka.serializers.json.KafkaJsonSchemaSerializer
  schema.registry.url: http://localhost:8081
```   

- from consumer-properties
```yml
  key.deserializer: org.apache.kafka.common.serialization.StringDeserializer
  value.deserializer: io.confluent.kafka.serializers.json.KafkaJsonSchemaDeserializer
  schema.registry.url: http://localhost:8081
  json.value.type: com.fasterxml.jackson.databind.JsonNode
``` 
  

