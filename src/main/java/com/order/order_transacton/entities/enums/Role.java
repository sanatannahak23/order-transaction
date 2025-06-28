package com.order.order_transacton.entities.enums;

public enum Role {
    CUSTOMER(1, "customer"), SUPPORT(2, "support"), ADMIN(3, "admin");

    private Integer value;
    private String role;

    Role(int value, String role) {
        this.value = value;
        this.role = role;
    }

    public Integer getValue() {
        return value;
    }

    public String getRole() {
        return role;
    }

    public static Role getByrole(String role) {
        for (Role roles : values()) {
            if (roles.getRole().equalsIgnoreCase(role)) return roles;
        }
        throw new RuntimeException("Invalid role...");
    }
}
