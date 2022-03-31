package com.cjrequena.sample.event;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.time.OffsetDateTime;

@Data
@NoArgsConstructor
@ToString(callSuper = true)
public class FooEvent extends Event {
  @Builder
  public FooEvent(
    String id,
    String source,
    String type,
    String dataContentType,
    String subject,
    OffsetDateTime time,
    Object data,
    String dataBase64,
    String dataSchema) {
    super(id, source, type, dataContentType, subject, time, data, dataBase64, dataSchema);
  }
}
