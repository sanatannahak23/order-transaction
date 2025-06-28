package com.order.order_transacton.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "orders",
        indexes = {
                @Index(name = "Idx_user", columnList = "user_id"),
                @Index(name = "Idx_product", columnList = "product_id"),
                @Index(name = "Idx_order_date", columnList = "order_data")
        })
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long orderId;

    @Column(name = "order_ref")
    private String orderRef;

    @Column(name = "order_data")
    private Long orderDate;

    @Column(name = "quantity")
    private Long orderQty;

    @Column(name = "total_price")
    private Double totalPrice;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    @PrePersist
    private void setOrderRef() {
        this.orderRef = "ord_" + UUID.randomUUID();
    }
}
