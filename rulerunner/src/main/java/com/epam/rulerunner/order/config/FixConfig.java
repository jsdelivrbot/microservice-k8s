package com.epam.rulerunner.order.config;

import com.epam.rulerunner.order.converter.FixEventToFixMessageConverter;
import com.epam.rulerunner.order.converter.FixMessageToOrderConverter;
import com.epam.rulerunner.order.service.FixMessageEnricher;
import com.epam.rulerunner.order.service.RuleEngine;
import com.epam.rulerunner.order.service.impl.DummyFixMessageEnricher;
import com.epam.rulerunner.order.service.impl.DummyRuleEngine;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixConfig {

    @Bean
    public FixMessageEnricher fixMessageEnricher() {
        return new DummyFixMessageEnricher();
    }

    @Bean
    public RuleEngine ruleEngine() {
        return new DummyRuleEngine();
    }

    @Bean
    public FixEventToFixMessageConverter fixEventToFixMessageConverter() {
        return new FixEventToFixMessageConverter();
    }

    @Bean
    public FixMessageToOrderConverter fixMessageToOrderConverter() {
        return new FixMessageToOrderConverter();
    }

}
