package com.epam.rulerunner.kafka.consumer.domain.exception;

public class EventProcessorNotFoundException extends RuntimeException {

    public EventProcessorNotFoundException(String message) {
        super(message);
    }

}
