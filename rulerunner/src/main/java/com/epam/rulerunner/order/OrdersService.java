package com.epam.rulerunner.order;

import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.repository.OrdersRepository;
import com.epam.rulerunner.order.repository.model.OrderEntity;
import org.springframework.core.convert.ConversionService;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

//@RequiredArgsConstructor
public class OrdersService {

    private final ConversionService conversionService;
    private final OrdersRepository ordersRepository;

    public OrdersService(ConversionService conversionService, OrdersRepository ordersRepository) {
        this.conversionService = conversionService;
        this.ordersRepository = ordersRepository;
    }

    public void sendOrder(Order order) {
        OrderEntity orderEntity = conversionService.convert(order, OrderEntity.class);
        ordersRepository.save(orderEntity);
        ordersRepository.flush();
    }

    public Optional<Order> getOrder(Long orderId) {
        Optional<OrderEntity> orderEntity = ordersRepository.findById(orderId);
        Optional<Order> order = orderEntity.map(c -> conversionService.convert(c, Order.class));
        return order;
    }

    public List<Order> getOrders() {
        List<OrderEntity> orderEntities = ordersRepository.findAll();
        List<Order> orders = orderEntities.stream().map(c -> conversionService.convert(c, Order.class)).collect(Collectors.toList());
        return orders;
    }
}
