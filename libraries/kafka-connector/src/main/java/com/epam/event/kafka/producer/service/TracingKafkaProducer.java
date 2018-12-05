package com.epam.event.kafka.producer.service;

//import lombok.RequiredArgsConstructor;
//import lombok.extern.slf4j.Slf4j;
//import org.apache.kafka.clients.consumer.OffsetAndMetadata;
//import org.apache.kafka.clients.producer.Callback;
//import org.apache.kafka.clients.producer.Producer;
//import org.apache.kafka.clients.producer.ProducerRecord;
//import org.apache.kafka.clients.producer.RecordMetadata;
//import org.apache.kafka.common.Metric;
//import org.apache.kafka.common.MetricName;
//import org.apache.kafka.common.PartitionInfo;
//import org.apache.kafka.common.TopicPartition;
//import org.springframework.cloud.sleuth.Span;
//
//import java.util.List;
//import java.util.Map;
//import java.util.concurrent.Future;
//import java.util.concurrent.TimeUnit;

//@Slf4j
//@RequiredArgsConstructor
public final class TracingKafkaProducer/*<K, V> implements Producer<K, V>*/ {
//    private final Producer<K, V> delegate;
//    private final KafkaProducerSpanService spanService;
//
//    @Override
//    public void initTransactions() {
//        delegate.initTransactions();
//    }
//
//    @Override
//    public void beginTransaction() {
//        delegate.beginTransaction();
//    }
//
//    @Override
//    public void commitTransaction() {
//        delegate.commitTransaction();
//    }
//
//    @Override
//    public void abortTransaction() {
//        delegate.abortTransaction();
//    }
//
//    @Override
//    public Future<RecordMetadata> send(ProducerRecord<K, V> record) {
//        return this.send(record, null);
//    }
//
//    @Override
//    public Future<RecordMetadata> send(ProducerRecord<K, V> record, Callback callback) {
//        Span span = spanService.createSpanBasedOnCurrent();
//        spanService.injectHeaders(span, record.headers());
//        return spanService.runFunctionAndCloseSpan(span, () -> delegate.send(record));
//    }
//
//    @Override
//    public void flush() {
//        delegate.flush();
//    }
//
//    @Override
//    public List<PartitionInfo> partitionsFor(String topic) {
//        return delegate.partitionsFor(topic);
//    }
//
//    @Override
//    public Map<MetricName, ? extends Metric> metrics() {
//        return delegate.metrics();
//    }
//
//    @Override
//    public void close() {
//        delegate.close();
//    }
//
//    @Override
//    public void close(long timeout, TimeUnit unit) {
//        delegate.close(timeout, unit);
//    }
//
//    @Override
//    public void sendOffsetsToTransaction(Map<TopicPartition, OffsetAndMetadata> offsets, String consumerGroupId) {
//        delegate.sendOffsetsToTransaction(offsets, consumerGroupId);
//    }
}
