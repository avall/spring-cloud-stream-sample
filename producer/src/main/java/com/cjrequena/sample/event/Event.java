package com.cjrequena.sample.event;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

import java.time.OffsetDateTime;

@Data
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
@JsonNaming(PropertyNamingStrategy.LowerCaseStrategy.class)
@ToString(callSuper = true)
public abstract class Event<T> {

  // Unique id for the specific message. This id is globally unique
  protected String id;

  // Identifies the context in which an event happened.
  protected String source;

  // The version of the CloudEvents specification which the event uses.
  protected final String specVersion = "1.0";

  // Type of message
  protected String type;

  // Content type of the data value. Must adhere to RFC 2046 format.
  public String dataContentType;

  // Describes the subject of the event in the context of the event producer (identified by source).
  protected String subject;

  // Date and time for when the message was published
  protected OffsetDateTime time;

  // The event payload.
  protected T data;

  // Base64 encoded event payload. Must adhere to RFC4648.
  protected String dataBase64;

  // Identifies the schema that data adheres to.
  protected String dataSchema;

}