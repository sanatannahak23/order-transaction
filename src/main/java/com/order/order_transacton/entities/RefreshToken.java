package com.order.order_transacton.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.Instant;

@Entity
@Table(name = "refresh_token")
@Builder
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RefreshToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String token;

    @Column(nullable = false, name = "issue_at")
    private Instant issuedAt;

    @Column(nullable = false, name = "exp_date")
    private Instant expiryData;

    @OneToOne
    private Credential credential;

}
