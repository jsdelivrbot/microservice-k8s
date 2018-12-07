package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.model.FixMessage;
import com.epam.rulerunner.order.service.RuleEngine;

public class DummyRuleEngine implements RuleEngine {

    @Override
    public FixMessage evaluateRules(FixMessage message) {
        return message;
    }

}
