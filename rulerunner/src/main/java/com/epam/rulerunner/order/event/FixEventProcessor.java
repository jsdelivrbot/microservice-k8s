package com.epam.rulerunner.order.event;

import com.epam.rulerunner.event.consumer.EventProcessor;
import com.epam.rulerunner.event.model.Event;
import com.epam.rulerunner.order.OrdersService;
import com.epam.rulerunner.order.model.FixMessage;
import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.service.FixMessageEnricher;
import com.epam.rulerunner.order.service.RuleEngine;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.ConversionService;
import org.springframework.stereotype.Component;

@Component
public class FixEventProcessor implements EventProcessor<FixEvent> {

    private final static Logger LOG = LoggerFactory.getLogger(FixEventProcessor.class);

    private final ConversionService conversionService;
    private final FixMessageEnricher enricher;
    private final RuleEngine rules;
    private final OrdersService service;

    @Autowired
    public FixEventProcessor(ConversionService conversionService, FixMessageEnricher enricher, RuleEngine rules, OrdersService service) {
        this.conversionService = conversionService;
        this.enricher = enricher;
        this.rules = rules;
        this.service = service;
    }

    @Override
    public void process(Event event, FixEvent payload) {
        LOG.info("FIX message processed: {}", payload);
        FixMessage message = conversionService.convert(payload, FixMessage.class);
        FixMessage result = rules.evaluateRules(enricher.enrich(message));
        Order order = conversionService.convert(result, Order.class);
        service.sendOrder(order);
    }

}
