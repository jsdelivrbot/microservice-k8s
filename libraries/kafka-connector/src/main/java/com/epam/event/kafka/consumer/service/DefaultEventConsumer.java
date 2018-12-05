package com.epam.event.kafka.consumer.service;

import com.epam.event.consumer.MessageReceiver;
import com.epam.event.kafka.consumer.domain.KafkaEvent;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

//@Slf4j
//@RequiredArgsConstructor
public class DefaultEventConsumer implements EventConsumer {

    private final MessageReceiver messageReceiver;

    public DefaultEventConsumer(MessageReceiver messageReceiver) {
        this.messageReceiver = messageReceiver;
    }

    @Override
    public void consume(KafkaEvent event) {
        messageReceiver.receive(event.getEvent());
    }
}
