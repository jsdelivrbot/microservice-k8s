package com.epam.rulerunner.order.service;

import com.epam.rulerunner.order.model.Order;

public interface RuleEngine {

    Order evaluateRules(Order message);

}
