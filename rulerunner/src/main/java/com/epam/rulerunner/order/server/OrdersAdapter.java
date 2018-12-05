package com.epam.rulerunner.order.server;


import com.epam.rulerunner.order.OrdersService;
import com.epam.rulerunner.order.model.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
public class OrdersAdapter implements OrdersApiDelegate {

    private final OrdersService orderService;

    public OrdersAdapter(OrdersService orderService) {
        this.orderService = orderService;
    }

    @Override
    public ResponseEntity<Void> sendOrder(Order order) {
        orderService.sendOrder(order);

        return ResponseEntity.accepted().build();
    }

    @Override
    public ResponseEntity<List<Order>> getOrders() {
        List<Order> orders = orderService.getOrders();

        return ResponseEntity.ok(orders);
    }

    @Override
    public ResponseEntity<Order> getOrders(Long orderId) {
        Optional<Order> order = orderService.getOrder(orderId);

        return order.isPresent() ? ResponseEntity.ok(order.get()) : ResponseEntity.notFound().build();
    }

    private <T> ResponseEntity<T> notImplemented() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
