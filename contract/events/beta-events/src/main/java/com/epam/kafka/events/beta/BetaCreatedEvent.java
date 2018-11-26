package com.epam.kafka.events.beta;

import lombok.Builder;
import lombok.RequiredArgsConstructor;
import lombok.Value;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;

@Value
@Builder
public class BetaCreatedEvent {

    private final String id;
    private final String stringValue;
    private final Long longValue;
    private final Double doubleValue;
    private final BigDecimal bigDecimalValue;
    private final Boolean standard;
    private final LocalDate localDateValue;
    private final LocalDateTime localDateTimeValue;

}
