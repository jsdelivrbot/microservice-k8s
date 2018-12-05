package com.epam.event.kafka.producer.service;

import com.epam.event.kafka.producer.config.KafkaCommonTopicConfiguration;
import com.epam.event.producer.MessageSender;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.core.KafkaTemplate;

//@Slf4j
//@RequiredArgsConstructor
public class KafkaMessageSender implements MessageSender {
    private final static Logger LOG = LoggerFactory.getLogger(KafkaMessageSender.class);

    private final KafkaTemplate<String, String> kafkaProducer;
    private final KafkaCommonTopicConfiguration configuration;

    public KafkaMessageSender(KafkaTemplate<String, String> kafkaProducer, KafkaCommonTopicConfiguration configuration) {
        this.kafkaProducer = kafkaProducer;
        this.configuration = configuration;
    }

    @Override
    public void send(String event) {
        LOG.info("Event {} sent to kafka {} topic", event, configuration.getCommonTopic());
        kafkaProducer.send(configuration.getCommonTopic(), event);
    }
}