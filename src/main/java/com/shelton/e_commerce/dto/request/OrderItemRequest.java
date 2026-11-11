package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class OrderItemRequest {
    private Integer quantity;
    private BigDecimal unitPrice;
    private UUID orderId;
    private UUID productId;
}
