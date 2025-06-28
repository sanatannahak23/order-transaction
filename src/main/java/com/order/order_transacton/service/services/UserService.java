package com.order.order_transacton.service.services;

import com.order.order_transacton.dto.request.LoginRequest;
import com.order.order_transacton.dto.request.UserRequest;
import com.order.order_transacton.entities.User;

import java.util.List;

public interface UserService {

    User register(UserRequest userRequest);

    String login(LoginRequest loginRequest);

    List<User> getAll();
}
