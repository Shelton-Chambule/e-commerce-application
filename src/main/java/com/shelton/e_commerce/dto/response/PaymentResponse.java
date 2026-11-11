package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import java.math.BigDecimal;
import com.shelton.e_commerce.entity.enums.PaymentStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PaymentResponse {
    private UUID id;
    private PaymentStatus status;
    private BigDecimal amount;
    private UUID orderId;
    private UUID transactionId;
}

