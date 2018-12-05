package com.epam.event.kafka.consumer.config;

import com.epam.event.consumer.*;
import com.epam.event.kafka.consumer.domain.exception.EventProcessorNotFoundException;
import com.epam.event.kafka.consumer.service.*;
import com.epam.event.kafka.jackson.JacksonConfig;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import org.apache.kafka.clients.consumer.ConsumerConfig;
import org.apache.kafka.common.serialization.StringDeserializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
//import org.springframework.cloud.sleuth.Tracer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ClassPathScanningCandidateComponentProvider;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;
import org.springframework.core.env.AbstractEnvironment;
import org.springframework.core.env.EnumerablePropertySource;
import org.springframework.core.env.Environment;
import org.springframework.core.env.MutablePropertySources;
import org.springframework.core.type.filter.RegexPatternTypeFilter;
import org.springframework.kafka.annotation.EnableKafka;
import org.springframework.kafka.config.ConcurrentKafkaListenerContainerFactory;
import org.springframework.kafka.core.DefaultKafkaConsumerFactory;
import org.springframework.kafka.support.serializer.JsonDeserializer;

import java.lang.reflect.ParameterizedType;
import java.util.*;
import java.util.regex.Pattern;
import java.util.stream.StreamSupport;

//@Slf4j
@Configuration
@EnableKafka
@Import(JacksonConfig.class)
public class KafkaConsumerConfiguration {

    private static final Logger LOG = LoggerFactory.getLogger(KafkaConsumerConfiguration.class);

    private static final String EARLIEST = "earliest";
    private static final String PREFIX = "kafka.configs.";

    @Value("${kafka.connection.host}")
    private String kafkaHost;

    @Value("${kafka.connection.port}")
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
    //@ConditionalOnExpression("'${spring.zipkin.enabled:false}' == 'false'")
    @ConditionalOnMissingBean(EventConsumer.class)
    public EventConsumer eventConsumer(MessageReceiver messageReceiver) {
        return new DefaultEventConsumer(messageReceiver);
    }

//    @Bean
//    @ConditionalOnExpression("'${spring.zipkin.enabled:false}' == 'true'")
//    @ConditionalOnMissingBean(EventConsumer.class)
//    public EventConsumer eventTracingConsumerService(Tracer spanTracer, MessageReceiver messageReceiver) {
//        DefaultEventConsumer defaultEventConsumer = new DefaultEventConsumer(messageReceiver);
//        return new TracingEventConsumer(new KafkaConsumerSpanService(spanTracer, serviceName), defaultEventConsumer);
//    }

    @Bean
    public ConcurrentKafkaListenerContainerFactory<String, String> eventContainerFactory(ImmutableMap<String, Object> kafkaConfiguration) {
        DefaultKafkaConsumerFactory<String, String> consumerFactory = new DefaultKafkaConsumerFactory<>(kafkaConfiguration, new StringDeserializer(), new StringDeserializer());
        ConcurrentKafkaListenerContainerFactory<String, String> factory = new ConcurrentKafkaListenerContainerFactory<>();
        factory.setConsumerFactory(consumerFactory);
        return factory;
    }

    @Bean
    public MessageReceiver messageReceiver(List<EventProcessor> eventProcessors, PackageConfiguration packageConfiguration, ObjectMapper objectMapper) {
        ClassPathScanningCandidateComponentProvider provider = new ClassPathScanningCandidateComponentProvider(false);
        provider.addIncludeFilter(new RegexPatternTypeFilter(Pattern.compile(".*Event")));
        final Set<BeanDefinition> classes = new HashSet<>();
        packageConfiguration.getPackages().forEach(s -> classes.addAll(provider.findCandidateComponents(s)));
        Map<Class, EventHandler> eventHandlers = new HashMap<>();
        for (BeanDefinition bean : classes) {
            try {
                Class<?> eventClass = Class.forName(bean.getBeanClassName());
                EventProcessor eventProcessor = eventProcessors
                        .stream()
                        .filter(processor -> getTypeName(processor).equals(bean.getBeanClassName()))
                        .findFirst()
                        .orElseThrow(EventProcessorNotFoundException::new);
                eventHandlers.put(eventClass, new JsonEventProcessorDelegate<>(eventClass, eventProcessor, objectMapper));
            } catch (ClassNotFoundException e) {
                LOG.error("Event class not found", e);
                throw new RuntimeException(e);
            }
        }

        EventFilter eventFilter = new ClassSimpleNameWhitelistEventFilter(eventHandlers.keySet());
        EventHandler eventHandler = new ClassSimpleNameRoutingEventHandler(eventHandlers);
        return new JsonMessageReceiver(eventFilter, eventHandler, objectMapper);
    }

    private String getTypeName(EventProcessor eventProcessor) {
        return ((ParameterizedType) eventProcessor.getClass().getGenericInterfaces()[0]).getActualTypeArguments()[0].getTypeName();
    }
}

