package com.epam.rulerunner.order.service;

import com.epam.rulerunner.order.model.FixMessage;

public interface RuleEngine {

    FixMessage evaluateRules(FixMessage message);

}
