package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.service.OrderEnricher;

public class DummyOrderEnricher implements OrderEnricher {
    @Override
    public Order enrich(Order order) {
        return order;
    }
}
