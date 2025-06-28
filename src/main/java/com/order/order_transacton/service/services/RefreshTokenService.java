package com.order.order_transacton.service.services;

import com.order.order_transacton.dto.request.RefreshTokenRequest;
import com.order.order_transacton.entities.RefreshToken;

public interface RefreshTokenService {

    public RefreshToken createToken(String userName);

    Boolean validateToken(RefreshToken refreshToken);

    RefreshToken findByToken(RefreshTokenRequest tokenRequest);

    void delete(RefreshToken refreshToken);

}
