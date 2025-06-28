package com.order.order_transacton.repository;

import com.order.order_transacton.entities.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Long> {
}
