package com.epam.rulerunner.order.config;

import com.epam.rulerunner.order.OrdersService;
import com.epam.rulerunner.order.converter.OrderEntityToOrderConverter;
import com.epam.rulerunner.order.converter.OrderToOrderEntityConverter;
import com.epam.rulerunner.order.repository.OrdersRepository;
import com.epam.rulerunner.order.server.OrdersAdapter;
import com.epam.rulerunner.order.service.RuleEngine;
import com.epam.rulerunner.order.service.impl.DummyRuleEngine;
import org.kie.api.runtime.KieContainer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.convert.ConversionService;

@Configuration
public class OrdersConfig {

//    @Bean
//    public OrdersApiController ordersApiController(OrdersAdapter ordersAdapter) {
//        return new OrdersApiController(ordersAdapter);
//    }

    @Bean
    public OrdersAdapter ordersAdapter(OrdersService ordersService, RuleEngine ruleEngine) {
        return new OrdersAdapter(ordersService, ruleEngine);
    }

    @Bean
    public OrdersService ordersService(ConversionService conversionService, OrdersRepository ordersRepository) {
        return new OrdersService(conversionService, ordersRepository);
    }

    @Bean
    public OrderToOrderEntityConverter orderToOrderEntityConverter() {
        return new OrderToOrderEntityConverter();
    }

    @Bean
    public OrderEntityToOrderConverter orderEntityToOrderConverter() {
        return new OrderEntityToOrderConverter();
    }

    @Bean
    public RuleEngine ruleEngine(KieContainer kieContainer) {
        return new DummyRuleEngine(kieContainer);
    }


}
