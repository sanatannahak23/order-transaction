package com.order.order_transacton.repository;

import com.order.order_transacton.entities.Credential;
import com.order.order_transacton.entities.RefreshToken;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface RefreshTokenRepository extends JpaRepository<RefreshToken, Long> {

    Optional<RefreshToken> findByToken(String token);

    Optional<RefreshToken> findByCredential(Credential credential);
}
