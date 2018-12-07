package com.epam.rulerunner.order.converter;

import com.epam.rulerunner.order.model.FixMessage;
import com.epam.rulerunner.order.model.Order;
import org.springframework.core.convert.converter.Converter;

public class FixMessageToOrderConverter implements Converter<FixMessage, Order> {

    @Override
    public Order convert(FixMessage source) {
        Order order = new Order();
        order.setAccount("USD");
        order.setAmount(1000);
        order.setCcy("USD");
        order.setType("BUY");
        order.setRawFixMessage(source.getRawFixMessage());
        return order;
    }

}
