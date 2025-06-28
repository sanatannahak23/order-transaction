package com.order.order_transacton.controller;

import com.order.order_transacton.dto.request.LoginRequest;
import com.order.order_transacton.dto.request.RefreshTokenRequest;
import com.order.order_transacton.dto.request.UserRequest;
import com.order.order_transacton.dto.response.ApiResponse;
import com.order.order_transacton.dto.response.AuthResponse;
import com.order.order_transacton.entities.RefreshToken;
import com.order.order_transacton.messages.SuccessMessages;
import com.order.order_transacton.security.JwtTokenService;
import com.order.order_transacton.service.services.RefreshTokenService;
import com.order.order_transacton.service.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserService userService;

    private final JwtTokenService jwtTokenService;

    private final RefreshTokenService refreshTokenService;

    @PostMapping("/register")
    public ResponseEntity<ApiResponse> register(@RequestBody UserRequest userRequest) {
        return ResponseEntity
                .ok()
                .body(new ApiResponse(Boolean.FALSE,
                        userService.register(userRequest),
                        SuccessMessages.USER_REGISTER_SUCCESSFULLY)
                );
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest loginRequest) {
        AuthResponse authResponse = userService.login(loginRequest);
        return ResponseEntity
                .ok()
                .body(AuthResponse
                        .builder()
                        .accessToken(authResponse.getAccessToken())
                        .token(authResponse.getToken())
                        .build());
    }

    @PostMapping("/refresh-token")
    public ResponseEntity<AuthResponse> refreshToken(@RequestBody RefreshTokenRequest tokenRequest) {
        RefreshToken refreshToken = refreshTokenService.findByToken(tokenRequest);
        refreshTokenService.validateToken(refreshToken);
        String accessToken = jwtTokenService.getToken(refreshToken.getCredential().getEmail(),
                refreshToken.getCredential().getRole().getRole());

        return ResponseEntity
                .ok()
                .body(AuthResponse
                        .builder()
                        .accessToken(accessToken)
                        .token(refreshToken.getToken())
                        .build());
    }
}
