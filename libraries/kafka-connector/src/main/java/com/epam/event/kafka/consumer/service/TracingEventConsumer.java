package com.epam.event.kafka.consumer.service;

//import com.epam.event.kafka.consumer.domain.KafkaEvent;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.sleuth.Span;

//@Slf4j
//@RequiredArgsConstructor
public class TracingEventConsumer /*implements EventConsumer*/ {
//    private final KafkaConsumerSpanService kafkaConsumerSpanService;
//    private final EventConsumer delegate;
//
//    public TracingEventConsumer(KafkaConsumerSpanService kafkaConsumerSpanService, EventConsumer delegate) {
//        this.kafkaConsumerSpanService = kafkaConsumerSpanService;
//        this.delegate = delegate;
//    }
//
//    @Override
//    public void consume(KafkaEvent event) {
//        Span span = kafkaConsumerSpanService.createSpanFromKafkaByteSpan(event.getSpan());
//        Span currentSpan = kafkaConsumerSpanService.createSpanAndLogEvent(span);
//
//        delegate.consume(event);
//
//        kafkaConsumerSpanService.close(currentSpan);
//    }
}
