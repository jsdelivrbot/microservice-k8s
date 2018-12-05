package com.epam.rulerunner.order.converter;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.repository.model.OrderEntity;
import org.springframework.core.convert.converter.Converter;

public class OrderToOrderEntityConverter implements Converter<Order, OrderEntity> {

    @Override
    public OrderEntity convert(Order source) {

        OrderEntity orderEntity = new OrderEntity();
        
        orderEntity.setId(source.getId());
        orderEntity.setType(source.getType());
        orderEntity.setAccount(source.getAccount());
        orderEntity.setAmount(source.getAmount());
        orderEntity.setCcy(source.getCcy());

        return orderEntity;
    }
}
