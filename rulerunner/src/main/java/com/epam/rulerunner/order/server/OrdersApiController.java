package com.epam.rulerunner.order.server;

import org.springframework.stereotype.Controller;

@Controller
public class OrdersApiController implements OrdersApi {

    private final OrdersApiDelegate delegate;

    @org.springframework.beans.factory.annotation.Autowired
    public OrdersApiController(OrdersApiDelegate delegate) {
        this.delegate = delegate;
    }

    @Override
    public OrdersApiDelegate getDelegate() {
        return delegate;
    }
}
