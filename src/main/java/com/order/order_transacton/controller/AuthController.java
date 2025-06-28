package com.order.order_transacton.controller;

import com.order.order_transacton.dto.request.LoginRequest;
import com.order.order_transacton.dto.request.UserRequest;
import com.order.order_transacton.dto.response.ApiResponse;
import com.order.order_transacton.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity
                .ok()
                .body(new ApiResponse(Boolean.FALSE,
                        userService.register(userRequest),
                        "User Register Successfully")
                );
    }

    @GetMapping("/login")
    public ResponseEntity<ApiResponse> login(@RequestBody LoginRequest loginRequest) {
        return ResponseEntity
                .ok()
                .body(new ApiResponse(Boolean.FALSE,
                        userService.login(loginRequest),
                        "Access Token"));
    }
}
