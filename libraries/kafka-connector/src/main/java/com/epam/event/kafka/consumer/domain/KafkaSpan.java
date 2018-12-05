package com.epam.event.kafka.consumer.domain;

import lombok.Builder;
import lombok.Getter;

@Getter
@Builder
public class KafkaSpan {
    private String traceId;
    private String spanId;
    private String flags;
    private String sampledName;
    private String spanName;
    private String parentId;
}
