package com.order.order_transacton.controller;

import com.order.order_transacton.dto.request.OrderRequest;
import com.order.order_transacton.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/create-order")
    public ResponseEntity<?> order(@RequestBody OrderRequest orderRequest) {
        return ResponseEntity
                .ok(orderService.createOrder(orderRequest));
    }
}
