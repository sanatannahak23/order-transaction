package com.order.order_transacton.repository;

import com.order.order_transacton.entities.Product;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductRepository extends JpaRepository<Product, Long> {
}
