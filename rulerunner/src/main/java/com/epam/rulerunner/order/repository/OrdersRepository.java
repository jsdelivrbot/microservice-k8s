package com.epam.rulerunner.order.repository;

import org.springframework.data.jpa.repository.JpaRepository;

public interface OrdersRepository extends JpaRepository<com.epam.rulerunner.order.repository.model.OrderEntity, String> {

}
