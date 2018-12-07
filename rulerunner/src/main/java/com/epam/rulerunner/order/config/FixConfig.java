package com.epam.rulerunner.order.config;

import com.epam.rulerunner.order.converter.FixEventToOrderConverter;
import com.epam.rulerunner.order.service.OrderEnricher;
import com.epam.rulerunner.order.service.RuleEngine;
import com.epam.rulerunner.order.service.impl.DummyOrderEnricher;
import com.epam.rulerunner.order.service.impl.DummyRuleEngine;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FixConfig {

    @Bean
    public OrderEnricher orderEnricher() {
        return new DummyOrderEnricher();
    }

    @Bean
    public FixEventToOrderConverter fixEventToOrderConverter() {
        return new FixEventToOrderConverter();
    }

}
