package com.order.Order_service.dto;

import java.math.BigDecimal;

public record OrderRequest(
        String skuCode,
        Integer quantity,
        BigDecimal price
){
}
