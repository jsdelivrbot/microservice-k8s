package com.epam.event.kafka.consumer.domain;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class KafkaByteSpan {
    private byte[] traceId;
    private byte[] spanId;
    private byte[] flags;
    private byte[] sampledName;
    private byte[] spanName;
    private byte[] parentId;
}
