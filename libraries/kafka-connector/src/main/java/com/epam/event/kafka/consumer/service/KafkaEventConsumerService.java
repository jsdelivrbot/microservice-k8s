package com.epam.event.kafka.consumer.service;

import com.epam.event.kafka.consumer.domain.KafkaByteSpan;
import com.epam.event.kafka.consumer.domain.KafkaEvent;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
//import org.springframework.cloud.sleuth.instrument.messaging.TraceMessageHeaders;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.messaging.handler.annotation.Header;
import org.springframework.messaging.handler.annotation.Payload;

//@RequiredArgsConstructor
public class KafkaEventConsumerService {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaEventConsumerService.class);

    private final EventConsumer delegate;

    public KafkaEventConsumerService(EventConsumer delegate) {
        this.delegate = delegate;
    }

    @KafkaListener(topics = "${kafka.common_topic:default_topic}", containerFactory = "eventContainerFactory", errorHandler = "kafkaErrorHandler")
    public void consumeEvent(/*@Header(value = TraceMessageHeaders.TRACE_ID_NAME, required = false) byte[] traceIdByte,
                                 @Header(value = TraceMessageHeaders.SPAN_ID_NAME, required = false) byte[] spanIdByte,
                                 @Header(value = TraceMessageHeaders.SPAN_FLAGS_NAME, required = false) byte[] flagsByte,
                                 @Header(value = TraceMessageHeaders.SAMPLED_NAME, required = false) byte[] sampledNameByte,
                                 @Header(value = TraceMessageHeaders.SPAN_NAME_NAME, required = false) byte[] spanNameByte,
                                 @Header(value = TraceMessageHeaders.PARENT_ID_NAME, required = false) byte[] parentIdByte,*/
                                 @Payload ConsumerRecord event) {
        LOG.info(event.toString());
        if(!(event.value() instanceof String)){
            throw new IllegalStateException("Event is not a string");
        }

//        KafkaByteSpan byteSpan = KafkaByteSpan.builder()
//            .traceId(traceIdByte)
//            .spanId(spanIdByte)
//            .flags(flagsByte)
//            .sampledName(sampledNameByte)
//            .spanName(spanNameByte)
//            .parentId(parentIdByte)
//            .build();

        //LOG.info(byteSpan.toString());

        KafkaEvent e = new KafkaEvent();
        e.setEvent((String)event.value());

        delegate.consume(e);
    }
}
