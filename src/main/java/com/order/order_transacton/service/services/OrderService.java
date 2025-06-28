package com.order.order_transacton.service.services;

import com.order.order_transacton.dto.request.OrderRequest;
import com.order.order_transacton.entities.Order;

public interface OrderService {
    Order createOrder(OrderRequest orderRequest);
}
