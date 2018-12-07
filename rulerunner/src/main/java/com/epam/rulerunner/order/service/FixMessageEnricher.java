package com.epam.rulerunner.order.service;

import com.epam.rulerunner.order.model.FixMessage;

public interface FixMessageEnricher {

    FixMessage enrich(FixMessage message);

}
