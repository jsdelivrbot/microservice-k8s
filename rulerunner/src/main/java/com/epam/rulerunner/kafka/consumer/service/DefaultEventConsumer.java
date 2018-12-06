package com.epam.rulerunner.kafka.consumer.service;

import com.epam.rulerunner.event.consumer.MessageReceiver;
import com.epam.rulerunner.kafka.consumer.domain.KafkaEvent;

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
