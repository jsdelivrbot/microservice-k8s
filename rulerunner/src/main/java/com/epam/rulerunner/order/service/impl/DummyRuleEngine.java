package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.service.RuleEngine;

public class DummyRuleEngine implements RuleEngine {

    @Override
    public Order evaluateRules(Order order) {
        return order;
    }

}
