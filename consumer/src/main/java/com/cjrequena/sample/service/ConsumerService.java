package com.cjrequena.sample.service;

import com.cjrequena.sample.event.FooEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Service;

import java.util.function.Consumer;

@Slf4j
@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ConsumerService {

  @Bean
  public Consumer<FooEvent> consumer() {
    return event -> {
      log.info("New event notification: {}", event);
    };
  }
}
