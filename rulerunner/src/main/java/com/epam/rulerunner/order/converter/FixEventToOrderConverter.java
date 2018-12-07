package com.epam.rulerunner.order.converter;

import com.epam.rulerunner.order.event.FixEvent;
import com.epam.rulerunner.order.model.Order;
import org.springframework.core.convert.converter.Converter;

public class FixEventToOrderConverter implements Converter<FixEvent, Order> {

    @Override
    public Order convert(FixEvent source) {
        Order order = new Order();
        order.setAccount("USD");
        order.setAmount(1000);
        order.setCcy("USD");
        order.setType("BUY");
        order.setRawFixMessage(source.getFixMessage());
        return order;
    }

}
