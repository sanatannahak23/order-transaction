package com.order.order_transacton.entities;

import com.order.order_transacton.entities.enums.Role;
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
@Table(name = "users",
        indexes = {
                @Index(name = "Idx_user_ref", columnList = "user_ref"),
                @Index(name = "Idx_role", columnList = "role")
        })
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    @Column(name = "user_ref")
    private String userRef;

    private String userName;

    @Column(name = "role")
    @Enumerated(EnumType.STRING)
    private Role userRole;

    private String gender;

    @Column(unique = true, name = "phone_num")
    private Long phoneNum;

    @Column(unique = true, name = "email")
    private String email;

    @OneToMany(mappedBy = "user", fetch = FetchType.LAZY)
    private List<Order> orders;

    @OneToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Credential credential;

    @PrePersist
    private void setUserRef() {
        this.userRef = userRole.getRole() + "_" + UUID.randomUUID();
    }
}
