package com.epam.event.kafka.consumer.service;

import com.epam.event.kafka.consumer.domain.KafkaEvent;

public interface EventConsumer {
    void consume(KafkaEvent event);
}
