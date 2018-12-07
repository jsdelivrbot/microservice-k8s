package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.model.FixMessage;
import com.epam.rulerunner.order.service.FixMessageEnricher;

public class DummyFixMessageEnricher implements FixMessageEnricher {
    @Override
    public FixMessage enrich(FixMessage message) {
        return message;
    }
}
