package com.epam.rulerunner.kafka.consumer.config;

import com.epam.rulerunner.event.consumer.*;
import com.epam.rulerunner.kafka.consumer.domain.exception.EventProcessorNotFoundException;
import com.epam.rulerunner.kafka.consumer.service.DefaultEventConsumer;
import com.epam.rulerunner.kafka.consumer.service.EventConsumer;
import com.epam.rulerunner.kafka.consumer.service.KafkaErrorHandler;
import com.epam.rulerunner.kafka.consumer.service.KafkaEventConsumerService;
import com.epam.rulerunner.kafka.jackson.JacksonConfig;
import com.epam.rulerunner.order.event.FixEvent;
import com.epam.rulerunner.order.event.RawFixMessageReceiver;
import com.google.common.collect.ImmutableMap;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.convert.converter.Converter;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;

import java.lang.reflect.ParameterizedType;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.StreamSupport;

//import org.springframework.cloud.sleuth.Tracer;

//@Slf4j
@Configuration
@EnableKafka
@Import(JacksonConfig.class)
public class KafkaConsumerConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfiguration.class);

    private static final String EARLIEST = "earliest";
    private static final String PREFIX = "kafka.configs.";

    @Value("${kafka.host}")
    private String kafkaHost;

    @Value("${kafka.port}")
    private Integer kafkaPort;

    @Value("${kafka.group}")
    private String kafkaGroup;

    @Value("${spring.application.name}")
    private String serviceName;

    @Autowired
    private Environment environment;

    @Bean
    public ImmutableMap<String, Object> kafkaConfiguration() {
        return ImmutableMap.<String, Object>builder()
                .put(ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG, kafkaHost + ":" + kafkaPort)
                .put(ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG, StringDeserializer.class)
                .put(ConsumerConfig.GROUP_ID_CONFIG, kafkaGroup)
                .put(ConsumerConfig.AUTO_OFFSET_RESET_CONFIG, EARLIEST)
                .putAll(collectAllKafkaConsumerConfig())
                .build();
    }

    private ImmutableMap<String, Object> collectAllKafkaConsumerConfig() {
        ImmutableMap.Builder<String, Object> builder = ImmutableMap.builder();
        MutablePropertySources propSrcs = ((AbstractEnvironment) environment).getPropertySources();
        StreamSupport.stream(propSrcs.spliterator(), false)
                .filter(ps -> ps instanceof EnumerablePropertySource)
                .map(ps -> ((EnumerablePropertySource) ps).getPropertyNames())
                .flatMap(Arrays::<String>stream)
                .filter(propName -> propName.startsWith(PREFIX) &&
                        ConsumerConfig.configNames().contains(propName.substring(PREFIX.length())))
                .forEach(propName -> builder.put(propName.substring(PREFIX.length()), environment.getProperty(propName)));
        return builder.build();
    }

    @Bean
    public KafkaErrorHandler kafkaErrorHandler() {
        return new KafkaErrorHandler(kafkaGroup);
    }

    @Bean
    public KafkaEventConsumerService kafkaEventConsumerService(EventConsumer eventConsumer) {
        return new KafkaEventConsumerService(eventConsumer);
    }

    @Bean
    @ConditionalOnMissingBean(EventConsumer.class)
    public EventConsumer eventConsumer(MessageReceiver messageReceiver) {
        return new DefaultEventConsumer(messageReceiver);
    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> eventContainerFactory(ImmutableMap<String, Object> kafkaConfiguration) {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaConfiguration, new StringDeserializer(), new StringDeserializer());
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public MessageReceiver messageReceiver(List<EventProcessor<?>> eventProcessors) {
        return fixMessageReceiver(eventProcessors, ImmutableMap.of(FixEvent.class, FixEvent::new));
    }

    @SuppressWarnings("unchecked")
    private MessageReceiver fixMessageReceiver(List<EventProcessor<?>> eventProcessors, Map<Class<?>, Converter<String, ?>> eventConverters) {
        Map<Class, EventHandler> eventHandlers = new HashMap<>();
        for (Class<?> eventClass : eventConverters.keySet()) {
            eventHandlers.put(eventClass, createHandler(eventProcessors, (Class<Object>) eventClass, (Converter<String, Object>) eventConverters.get(eventClass)));
        }

        EventFilter eventFilter = new ClassSimpleNameWhitelistEventFilter(eventHandlers.keySet());
        EventHandler eventHandler = new ClassSimpleNameRoutingEventHandler(eventHandlers);
        return new RawFixMessageReceiver(eventFilter, eventHandler);
    }

    private <E> EventHandler createHandler(List<EventProcessor<?>> eventProcessors, Class<E> eventClass, Converter<String, E> eventConverter) {
        EventProcessor<E> processor = findProcessor(eventProcessors, eventClass);
        return (event, payload) -> processor.process(event, eventConverter.convert(payload));
    }

    @SuppressWarnings("unchecked")
    private <E> EventProcessor<E> findProcessor(List<EventProcessor<?>> eventProcessors, Class<E> eventClass) {
        return (EventProcessor<E>) eventProcessors
                            .stream()
                            .filter(processor -> getTypeName(processor).equals(eventClass.getName()))
                            .findFirst()
                            .orElseThrow(() -> new EventProcessorNotFoundException(eventClass.getName()));
    }

    private String getTypeName(EventProcessor eventProcessor) {
        return ((ParameterizedType) eventProcessor.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName();
    }
}

