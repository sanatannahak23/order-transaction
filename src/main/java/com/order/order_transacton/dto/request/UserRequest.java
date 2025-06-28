package com.order.order_transacton.dto.request;

import lombok.Data;

@Data
public class UserRequest {

    private String userName;

    private String role;

    private String gender;

    private Long phoneNum;

    private String email;

    private String password;
}
