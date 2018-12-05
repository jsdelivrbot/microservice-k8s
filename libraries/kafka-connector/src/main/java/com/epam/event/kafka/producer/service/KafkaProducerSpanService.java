package com.epam.event.kafka.producer.service;

//import lombok.RequiredArgsConstructor;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.apache.kafka.common.header.Headers;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//import org.springframework.cloud.sleuth.instrument.messaging.TraceMessageHeaders;
//
//import java.nio.charset.Charset;
//import java.util.concurrent.Future;
//import java.util.function.Supplier;

//@RequiredArgsConstructor
public class KafkaProducerSpanService {
//    private final Tracer spanTracer;
//    private final String serviceName;
//
//    public Span createSpanBasedOnCurrent() {
//        Span currentSpan = spanTracer.getCurrentSpan();
//        return spanTracer.createSpan(serviceName, currentSpan);
//    }
//
//    public Future<RecordMetadata> runFunctionAndCloseSpan(Span span, Supplier<Future<RecordMetadata>> function) {
//        span.logEvent("Kafka message sent");
//        Future<RecordMetadata> future = function.get();
//        spanTracer.close(span);
//        return future;
//    }
//
//    public void injectHeaders(Span span, Headers carrier) {
//        HEADER_SETTER.put(carrier, TraceMessageHeaders.TRACE_ID_NAME, span.traceIdString());
//        HEADER_SETTER.put(carrier, TraceMessageHeaders.SPAN_ID_NAME, Span.idToHex(span.getSpanId()));
//        if (span.isExportable()) {
//            Long parentId = span.getParents() == null || span.getParents().isEmpty() ? null : span.getParents().get(0);
//            if (parentId != null) {
//                HEADER_SETTER.put(carrier, TraceMessageHeaders.PARENT_ID_NAME, Span.idToHex(parentId));
//            }
//            HEADER_SETTER.put(carrier, TraceMessageHeaders.SPAN_NAME_NAME, span.getName());
//            HEADER_SETTER.put(carrier, TraceMessageHeaders.SAMPLED_NAME, Span.SPAN_SAMPLED);
//        } else {
//            HEADER_SETTER.put(carrier, TraceMessageHeaders.SAMPLED_NAME, Span.SPAN_NOT_SAMPLED);
//        }
//    }
//
//    interface Setter<C, K> {
//        void put(C carrier, K key, String value);
//    }
//
//    private static final Setter<Headers, String> HEADER_SETTER = (carrier, key, value) -> {
//        carrier.remove(key);
//        if (value != null) {
//            carrier.add(key, value.getBytes(Charset.forName("UTF-8")));
//        }
//    };
}
