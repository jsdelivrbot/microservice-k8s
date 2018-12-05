package com.epam.rulerunner.order.converter;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.repository.model.OrderEntity;
import org.springframework.core.convert.converter.Converter;

public class OrderEntityToOrderConverter implements Converter<OrderEntity, Order> {

    @Override
    public Order convert(OrderEntity source) {

        Order order = new Order();

        order.setId(source.getId());
        order.setType(source.getType());
        order.setAccount(source.getType());
        order.setAmount(source.getAmount());
        order.setCcy(source.getCcy());

        return order;
    }
}
