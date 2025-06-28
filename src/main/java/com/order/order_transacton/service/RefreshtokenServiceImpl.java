package com.order.order_transacton.service;

import com.order.order_transacton.dto.request.RefreshTokenRequest;
import com.order.order_transacton.entities.RefreshToken;
import com.order.order_transacton.exception.DataNotFoundException;
import com.order.order_transacton.exception.InvalidTokenException;
import com.order.order_transacton.exception.RefreshTokenExpireException;
import com.order.order_transacton.messages.ExceptionMessages;
import com.order.order_transacton.repository.CredentialRepository;
import com.order.order_transacton.repository.RefreshTokenRepository;
import com.order.order_transacton.service.services.RefreshTokenService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class RefreshtokenServiceImpl implements RefreshTokenService {

    private final RefreshTokenRepository refreshTokenRepository;

    private final CredentialRepository credentialRepository;

    @Override
    public RefreshToken createToken(String userName) {
        RefreshToken refreshToken = RefreshToken
                .builder()
                .credential(credentialRepository.findByEmail(userName)
                        .orElseThrow(() -> new DataNotFoundException(ExceptionMessages.USER_NOT_FOUND)))
                .token(UUID.randomUUID().toString())
                .expiryData(Instant.now().plusSeconds(3600))
                .build();
        return refreshTokenRepository.save(refreshToken);
    }

    @Override
    public RefreshToken findByToken(RefreshTokenRequest tokenRequest) {
        return refreshTokenRepository.findByToken(tokenRequest.getToken())
                .orElseThrow(() -> new InvalidTokenException(ExceptionMessages.INVALID_TOKEN));
    }

    @Override
    public Boolean validateToken(RefreshToken refreshToken) {
        if (Instant.now().compareTo(refreshToken.getExpiryData()) >= 0) {
            delete(refreshToken);
            throw new RefreshTokenExpireException(ExceptionMessages.REFRESH_TOKEN_EXPIRED);
        }
        return Boolean.TRUE;
    }

    @Override
    public void delete(RefreshToken refreshToken) {
        refreshTokenRepository.delete(refreshToken);
    }
}
