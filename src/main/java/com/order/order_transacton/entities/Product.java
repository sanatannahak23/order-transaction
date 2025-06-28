package com.order.order_transacton.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "products",
        indexes = {
                @Index(name = "Idx_prd_ref", columnList = "prod_ref"),
                @Index(name = "Idx_prd_name", columnList = "name")
        })
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "prod_id")
    private Long id;

    @Column(name = "prod_ref", unique = true)
    private String prodRef;

    private String name;

    @Column(name = "available_stock")
    private Long qty;

    private Double price;

    @OneToMany(mappedBy = "product", fetch = FetchType.LAZY)
    private List<Order> orders;

    @PrePersist
    private void setProdRef() {
        this.prodRef = "prod_" + UUID.randomUUID();
    }
}
