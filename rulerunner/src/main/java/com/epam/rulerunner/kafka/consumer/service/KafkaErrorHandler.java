package com.epam.rulerunner.kafka.consumer.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.kafka.listener.KafkaListenerErrorHandler;
import org.springframework.kafka.listener.ListenerExecutionFailedException;
import org.springframework.messaging.Message;

//@Slf4j
public class KafkaErrorHandler implements KafkaListenerErrorHandler {

    private final static Logger LOG = LoggerFactory.getLogger(KafkaErrorHandler.class);

    private static final String TOPIC = "kafka_receivedTopic";
    private static final String PARTITION = "kafka_receivedPartitionId";
    private static final String OFFSET = "kafka_offset";
    private static final String TIMESTAMP = "kafka_receivedTimestamp";
    private static final String SPAN_ID = "spanId";
    private static final String TRACE_ID = "spanTraceId";

    private String group;

    public KafkaErrorHandler(String group) {
        this.group = group;
    }

    @Override
    public Object handleError(Message<?> message, ListenerExecutionFailedException e) {
        String topic = (String) message.getHeaders().get(TOPIC);
        Integer partition = (Integer) message.getHeaders().get(PARTITION);
        Long offset = (Long) message.getHeaders().get(OFFSET);
        Long timestamp = (Long) message.getHeaders().get(TIMESTAMP);

        if (isZipkinDataPresent(message)) {
            String spanId = new String((byte[]) message.getHeaders().get(SPAN_ID));
            String spanTraceId = new String((byte[]) message.getHeaders().get(TRACE_ID));
            LOG.error("Error during event processing. Topic: {}, partition: {}, group: {}, offset: {}, timestamp: {}, spanId: {}, traceId: {}",
                    topic, partition, group, offset, timestamp, spanId, spanTraceId, e);

        } else {
            LOG.error("Error during event processing. Topic: {}, partition: {}, group: {}, offset: {}, timestamp: {}", topic, partition, group, offset, timestamp, e);
        }
        return null;
    }

    private boolean isZipkinDataPresent(Message<?> message) {
        return message.getHeaders().get(SPAN_ID) != null && message.getHeaders().get(TRACE_ID) != null;
    }
}
