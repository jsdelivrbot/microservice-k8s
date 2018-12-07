package com.epam.rulerunner.order.service.impl;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.service.RuleEngine;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;

public class DummyRuleEngine implements RuleEngine {

    private KieContainer kieContainer;

    public DummyRuleEngine(KieContainer kieContainer) {
        this.kieContainer = kieContainer;
    }

    @Override
    public Order evaluateRules(Order order) {
        KieSession kieSession = kieContainer.newKieSession();
        kieSession.insert(order);
        kieSession.fireAllRules();
        kieSession.dispose();
        return order;
    }

}
