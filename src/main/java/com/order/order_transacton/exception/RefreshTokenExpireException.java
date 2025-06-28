package com.order.order_transacton.exception;

public class RefreshTokenExpireException extends RuntimeException {
    public RefreshTokenExpireException(String message) {
        super(message);
    }
}
