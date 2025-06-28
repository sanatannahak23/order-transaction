package com.order.order_transacton.service;

import com.order.order_transacton.entities.Product;
import com.order.order_transacton.repository.ProductRepository;
import com.order.order_transacton.service.services.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Product addProduct(Product product) {
        return productRepository.save(product);
    }
}
