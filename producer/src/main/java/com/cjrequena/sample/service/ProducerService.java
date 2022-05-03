package com.cjrequena.sample.service;

import com.cjrequena.sample.dto.FooDTO;
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

  public void produce(FooDTO dto) {

    FooEvent event = new FooEvent();
    event.setId(String.valueOf(UUID.randomUUID()));
    event.setData(dto);
    streamBridge
      .send("producer-out-0", MessageBuilder.withPayload(event)
      .build());
    log.info("Event emitted {}", event);
  }
}
