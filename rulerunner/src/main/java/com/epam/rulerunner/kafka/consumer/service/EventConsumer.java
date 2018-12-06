package com.epam.rulerunner.kafka.consumer.service;

import com.epam.rulerunner.kafka.consumer.domain.KafkaEvent;

public interface EventConsumer {
    void consume(KafkaEvent event);
}
