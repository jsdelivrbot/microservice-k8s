package com.epam.event.kafka.producer.service;

//import org.apache.kafka.clients.producer.Producer;
//import org.springframework.kafka.core.DefaultKafkaProducerFactory;
//
//import java.util.Map;

public class KafkaTracingProducerFactory/*<K, V> extends DefaultKafkaProducerFactory<K, V>*/ {
//	private final KafkaProducerSpanService kafkaProducerSpanService;
//
//	public KafkaTracingProducerFactory(Map<String, Object> configs, KafkaProducerSpanService kafkaProducerSpanService) {
//		super(configs);
//		this.kafkaProducerSpanService = kafkaProducerSpanService;
//	}
//
//	@Override
//	protected Producer<K, V> createKafkaProducer() {
//		return new TracingKafkaProducer<>(super.createKafkaProducer(), kafkaProducerSpanService);
//	}
//
//	@Override
//	protected Producer<K, V> createTransactionalProducer() {
//		return new TracingKafkaProducer<>(super.createTransactionalProducer(), kafkaProducerSpanService);
//	}
}
