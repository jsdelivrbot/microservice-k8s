package com.epam.event.kafka.producer.config;

import com.epam.event.kafka.jackson.JacksonConfig;
import com.epam.event.kafka.producer.service.KafkaMessageSender;
//import com.epam.event.kafka.producer.service.KafkaProducerSpanService;
//import com.epam.event.kafka.producer.service.KafkaTracingProducerFactory;
import com.epam.event.producer.EventProducer;
import com.epam.event.producer.JsonEventProducer;
import com.epam.event.producer.MessageSender;
import com.epam.event.producer.RandomUuidGenerator;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
//import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.common.serialization.StringSerializer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
//import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.kafka.core.DefaultKafkaProducerFactory;
import org.springframework.kafka.core.KafkaTemplate;
//import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.util.Arrays;
import java.util.stream.StreamSupport;

//@Slf4j
@Configuration
@Import(JacksonConfig.class)
public class KafkaProducerConfiguration {
    private static final int RETRIES_CONFIG_VALUE = 0;
    private static final int LINGER_MS_CONFIG_VALUE = 1;
    private static final int BUFFER_MEMORY_CONFIG_VALUE = 33554432;
    private static final String PREFIX = "kafka.configs.";

    @Value("${kafka.connection.host}")
    private String kafkaHost;

    @Value("${kafka.connection.port}")
    private Integer kafkaPort;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private Environment environment;

    @Bean
    public ImmutableMap<String, Object> kafkaConfig() {
        return ImmutableMap.<String, Object>builder()
            .put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost + ":" + kafkaPort)
            .put(ProducerConfig.RETRIES_CONFIG, RETRIES_CONFIG_VALUE)
            .put(ProducerConfig.LINGER_MS_CONFIG, LINGER_MS_CONFIG_VALUE)
            .put(ProducerConfig.BUFFER_MEMORY_CONFIG, BUFFER_MEMORY_CONFIG_VALUE)
            .put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
            .put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, StringSerializer.class)
            .putAll(collectAllKafkaProducerConfig())
            .build();
    }

    private ImmutableMap<String, Object> collectAllKafkaProducerConfig() {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        MutablePropertySources propSrcs = ((AbstractEnvironment) environment).getPropertySources();
        StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::<String>stream)
                .filter(propName -> propName.startsWith(PREFIX) &&
                        ProducerConfig.configNames().contains(propName.substring(PREFIX.length())))
                .forEach(propName -> builder.put(propName.substring(PREFIX.length()), environment.getProperty(propName)));
        return builder.build();
    }

//    @Bean
//    @ConditionalOnExpression("'${spring.zipkin.enabled:false}' == 'true'")
//    public KafkaTemplate<String, String> kafkaTracingTemplate(final ImmutableMap<String, Object> kafkaConfig,
//                                                              KafkaProducerSpanService kafkaProducerSpanService) {
//        return new KafkaTemplate<>(new KafkaTracingProducerFactory(kafkaConfig, kafkaProducerSpanService));
//    }

//    @Bean
//    @ConditionalOnExpression("'${spring.zipkin.enabled:false}' == 'true'")
//    public KafkaProducerSpanService kafkaProducerSpanService(Tracer spanTracer) {
//        return new KafkaProducerSpanService(spanTracer, serviceName);
//    }

    @Bean
//    @ConditionalOnExpression("'${spring.zipkin.enabled:false}' == 'false'")
    public KafkaTemplate<String, String> kafkaCommonTemplate(final ImmutableMap<String, Object> kafkaConfig) {
        return new KafkaTemplate<>(new DefaultKafkaProducerFactory<String, String>(kafkaConfig));
    }

    @Bean
    public EventProducer eventProducer(MessageSender messageSender, ObjectMapper objectMapper) {
        RandomUuidGenerator randomUuidGenerator = new RandomUuidGenerator();
        return new JsonEventProducer(messageSender, serviceName, randomUuidGenerator, objectMapper);
    }

    @Bean
    public KafkaMessageSender kafkaMessageSender(KafkaTemplate<String, String> kafkaCommonTemplate,
                                                 KafkaCommonTopicConfiguration configuration) {
        return new KafkaMessageSender(kafkaCommonTemplate, configuration);
    }

    @Bean
    public KafkaCommonTopicConfiguration kafkaCommonTopicConfiguration() {
        return new KafkaCommonTopicConfiguration();
    }

}
