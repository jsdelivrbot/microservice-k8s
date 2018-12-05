package com.epam.event.kafka.consumer.service;

//import com.epam.event.kafka.consumer.domain.KafkaByteSpan;
//import com.epam.event.kafka.consumer.domain.KafkaSpan;
//import lombok.NoArgsConstructor;
//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.springframework.cloud.sleuth.Span;
//import org.springframework.cloud.sleuth.Tracer;
//
//import java.nio.charset.Charset;
//import java.util.function.Consumer;
//
//@Slf4j
//@RequiredArgsConstructor
public class KafkaConsumerSpanService {
//    private final Tracer spanTracer;
//    private final String serviceName;
//
//    private static final String MISSING_POSTFIX = "%s is missing!";
//    private static final Consumer<String> EXCEPTION_CONSUMER = s -> {throw new IllegalArgumentException(String.format(MISSING_POSTFIX, s));};
//    private static final Consumer<String> WARN_LOGGING_CONSUMER = s -> log.warn("{} field is missing!", s);
//
//    public Span createSpanFromKafkaByteSpan(KafkaByteSpan kafkaByteSpan) {
//        validateKafkaSpan(kafkaByteSpan);
//
//        KafkaSpan span = createKafkaSpanFromKafkaByteSpan(kafkaByteSpan);
//
//        boolean debug = Span.SPAN_SAMPLED.equals(span.getFlags());
//        boolean spanSampled = debug || Span.SPAN_SAMPLED.equals(span.getSampledName());
//
//        return Span.builder()
//            .traceIdHigh(span.getTraceId().length() == 32 ? Span.hexToId(span.getTraceId(), 0) : 0)
//            .traceId(Span.hexToId(span.getTraceId()))
//            .spanId(Span.hexToId(span.getSpanId()))
//            .parent(Span.hexToId(span.getParentId()))
//            .exportable(spanSampled)
//            .name(span.getSpanName())
//            .remote(true)
//            .build();
//    }
//
//    public Span createSpanAndLogEvent(Span span) {
//        Span currentSpan = spanTracer.createSpan(serviceName, span);
//        currentSpan.logEvent("Kafka message received");
//        return currentSpan;
//    }
//
//    public void close(Span span) {
//        spanTracer.close(span);
//    }
//
//    private KafkaSpan createKafkaSpanFromKafkaByteSpan(KafkaByteSpan kafkaByteSpan) {
//        return KafkaSpan.builder()
//            .spanId(convertToString(kafkaByteSpan.getSpanId()))
//            .spanName(convertToString(kafkaByteSpan.getSpanName()))
//            .flags(convertToString(kafkaByteSpan.getFlags()))
//            .parentId(convertToString(kafkaByteSpan.getParentId()))
//            .sampledName(convertToString(kafkaByteSpan.getSampledName()))
//            .traceId(convertToString(kafkaByteSpan.getTraceId()))
//            .build();
//    }
//
//    private void validateKafkaSpan(KafkaByteSpan kafkaByteSpan) {
//        if (kafkaByteSpan == null) {
//            throw new IllegalArgumentException();
//        }
//        isByteArrayEmptyOrNull(kafkaByteSpan.getTraceId(), () ->  EXCEPTION_CONSUMER.accept("TraceID"));
//        isByteArrayEmptyOrNull(kafkaByteSpan.getSpanId(), () ->  EXCEPTION_CONSUMER.accept("SpanId"));
//        isByteArrayEmptyOrNull(kafkaByteSpan.getParentId(), () -> EXCEPTION_CONSUMER.accept("ParentId"));
//
//        isByteArrayEmptyOrNull(kafkaByteSpan.getFlags(), () -> WARN_LOGGING_CONSUMER.accept("Flag"));
//        isByteArrayEmptyOrNull(kafkaByteSpan.getSampledName(), () -> WARN_LOGGING_CONSUMER.accept("SampledName"));
//        isByteArrayEmptyOrNull(kafkaByteSpan.getSpanName(), () -> WARN_LOGGING_CONSUMER.accept("SpanName"));
//    }
//
//    private String convertToString(byte[] byteArray) {
//        if (byteArray != null) {
//            return new String(byteArray, Charset.forName("UTF-8"));
//        }
//        return null;
//    }
//
//    private void isByteArrayEmptyOrNull(byte[] byteArray, Runnable function) {
//        if (byteArray == null || byteArray.length == 0) {
//            function.run();
//        }
//    }

}
