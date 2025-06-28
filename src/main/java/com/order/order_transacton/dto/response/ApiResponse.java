package com.order.order_transacton.dto.response;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class ApiResponse {

    private Boolean error;

    private Object data;

    private String message;

}
