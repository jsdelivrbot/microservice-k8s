package com.epam.event.kafka.consumer.domain;

import lombok.Builder;
import lombok.Data;

//@Data
//@Builder
public class KafkaEvent {
    private String event;
    private KafkaByteSpan span;

    public String getEvent() {
        return event;
    }

    public void setEvent(String event) {
        this.event = event;
    }

    public KafkaByteSpan getSpan() {
        return span;
    }

    public void setSpan(KafkaByteSpan span) {
        this.span = span;
    }
}
