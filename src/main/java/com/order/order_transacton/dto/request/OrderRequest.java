package com.order.order_transacton.dto.request;

import lombok.Data;

@Data
public class OrderRequest {

    private Long orderQty;

    private Long userId;

    private Long productId;
}
