package com.epam.rulerunner.order.server;


import com.epam.rulerunner.order.OrdersService;
import com.epam.rulerunner.order.model.Order;
import com.epam.rulerunner.order.service.RuleEngine;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.List;
import java.util.Optional;

//@RequiredArgsConstructor
public class OrdersAdapter implements OrdersApiDelegate {

    private final OrdersService orderService;
    private final RuleEngine ruleEngine;


    public OrdersAdapter(OrdersService orderService, RuleEngine ruleEngine) {
        this.orderService = orderService;
        this.ruleEngine = ruleEngine;
    }

    @Override
    public ResponseEntity<Void> sendOrder(Order order) {
            ruleEngine.evaluateRules(order);
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

        return order.map(ResponseEntity::ok).orElse(ResponseEntity.notFound().build());
    }

    private <T> ResponseEntity<T> notImplemented() {
        return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).build();
    }
}
