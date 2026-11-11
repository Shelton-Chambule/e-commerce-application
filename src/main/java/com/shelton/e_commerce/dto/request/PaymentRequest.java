package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import java.math.BigDecimal;
import com.shelton.e_commerce.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PaymentRequest {
    private PaymentStatus status;
    private BigDecimal amount;
    private UUID orderId;
}
