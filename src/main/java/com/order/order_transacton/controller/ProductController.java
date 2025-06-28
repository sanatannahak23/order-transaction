package com.order.order_transacton.controller;

import com.order.order_transacton.entities.Product;
import com.order.order_transacton.service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/product")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @PostMapping("/add-product")
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        return ResponseEntity
                .ok(productService.addProduct(product));
    }
}
