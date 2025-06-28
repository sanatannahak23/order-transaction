package com.order.order_transacton.service;

import com.order.order_transacton.dto.request.OrderRequest;
import com.order.order_transacton.entities.Order;
import com.order.order_transacton.entities.Product;
import com.order.order_transacton.entities.User;
import com.order.order_transacton.repository.OrderRepository;
import com.order.order_transacton.repository.ProductRepository;
import com.order.order_transacton.repository.UserRepository;
import com.order.order_transacton.service.services.OrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {

    private final UserRepository userRepository;

    private final ProductRepository productRepository;

    private final OrderRepository orderRepository;

    @Override
    @Transactional(propagation = Propagation.REQUIRED, isolation = Isolation.READ_COMMITTED)
    public Order createOrder(OrderRequest orderRequest) {
        // validate the user
        User user = userRepository.findById(orderRequest.getUserId())
                .orElseThrow(() -> new RuntimeException("Invalid user Id"));

        // validate the product
        Product product = productRepository.findById(orderRequest.getProductId())
                .orElseThrow(() -> new RuntimeException("Invalid user Id"));

        // validate the qty
        if (orderRequest.getOrderQty() > product.getQty())
            throw new RuntimeException("Stock is unavailable.");

        // calculate the total price and set in order and set user and product in order.
        Order order = new Order();
        order.setUser(user);
        order.setProduct(product);
        order.setOrderDate(System.currentTimeMillis());
        order.setOrderQty(orderRequest.getOrderQty());
        order.setTotalPrice(product.getPrice() * orderRequest.getOrderQty());

        // update the list of order in user and product
        user.getOrders().add(order);
        product.setQty(product.getQty() - order.getOrderQty());
        product.getOrders().add(order);
        userRepository.save(user);
        productRepository.save(product);

        return orderRepository.save(order);
    }
}
