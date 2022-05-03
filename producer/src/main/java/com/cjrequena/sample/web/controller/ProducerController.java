package com.cjrequena.sample.web.controller;

import com.cjrequena.sample.dto.FooDTO;
import com.cjrequena.sample.service.ProducerService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.enums.ParameterIn;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.sleuth.Span;
import org.springframework.cloud.sleuth.Tracer;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.util.UUID;

import static com.cjrequena.sample.common.Constants.VND_SAMPLE_SERVICE_V1;
import static org.apache.http.HttpHeaders.CACHE_CONTROL;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

/**
 * <p>
 * <p>
 * <p>
 * <p>
 *
 * @author cjrequena
 */
@SuppressWarnings("unchecked")
@Slf4j
@RestController
@RequestMapping(value = "/producer")
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class ProducerController {

  private final ProducerService producerService;
  private final Tracer tracer;

  @Operation(
    summary = "Command for new bank account creation ",
    description = "Command for new bank account creation ",
    parameters = {
      @Parameter(
        name = "accept-version",
        required = true,
        in = ParameterIn.HEADER,
        schema = @Schema(
          name = "accept-version",
          type = "string",
          allowableValues = {VND_SAMPLE_SERVICE_V1}
        )
      ),
    },
    requestBody = @io.swagger.v3.oas.annotations.parameters.RequestBody(required = true, content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE, schema = @Schema(implementation = FooDTO.class)))
  )
  @ApiResponses(
    value = {
      @ApiResponse(responseCode = "202", description = "Accepted - The request has been accepted for processing, but the processing has not been completed. The request might or might not eventually be acted upon, as it might be disallowed when processing actually takes place.."),
      @ApiResponse(responseCode = "400", description = "Bad Request - The data given in the POST failed validation. Inspect the response body for details."),
      @ApiResponse(responseCode = "401", description = "Unauthorized - The supplied credentials, if any, are not sufficient to access the resource."),
      @ApiResponse(responseCode = "408", description = "Request Timeout"),
      @ApiResponse(responseCode = "409", description = "Conflict - The request could not be processed because of conflict in the request"),
      @ApiResponse(responseCode = "429", description = "Too Many Requests - Your application is sending too many simultaneous requests."),
      @ApiResponse(responseCode = "500", description = "Internal Server Error - We couldn't create the resource. Please try again."),
      @ApiResponse(responseCode = "503", description = "Service Unavailable - We are temporarily unable. Please wait for a bit and try again. ")
    }
  )
  @PostMapping(
    path = "/produce",
    produces = {APPLICATION_JSON_VALUE}
  )
  @SneakyThrows
  public ResponseEntity<Void> produce(@Parameter @Valid @RequestBody FooDTO dto, BindingResult bindingResult, HttpServletRequest request, UriComponentsBuilder ucBuilder) {
    Span span = tracer.currentSpan();
    if (span != null) {
      log.info("Trace ID {}", span.context().traceId());
      log.info("Span ID {}", span.context().spanId());
    }
    dto.setId(UUID.randomUUID());
    this.producerService.produce(dto);
    // Headers
    HttpHeaders headers = new HttpHeaders();
    headers.set(CACHE_CONTROL, "no store, private, max-age=0");
    return new ResponseEntity<>(headers, HttpStatus.ACCEPTED);
  }
}
