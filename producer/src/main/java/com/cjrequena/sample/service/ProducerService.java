package com.cjrequena.sample.service;

import com.cjrequena.sample.event.FooEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.stream.function.StreamBridge;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerService {

  private final StreamBridge streamBridge;

  public void producer() {
    FooEvent fooEvent = new FooEvent();
    fooEvent.setId(String.valueOf(UUID.randomUUID()));
    streamBridge
      .send("producer-out-0", MessageBuilder.withPayload(fooEvent)
      .build());
  }
}
