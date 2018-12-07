package com.epam.rulerunner.order.service;

import com.epam.rulerunner.order.model.Order;

public interface OrderEnricher {

    Order enrich(Order message);

}
