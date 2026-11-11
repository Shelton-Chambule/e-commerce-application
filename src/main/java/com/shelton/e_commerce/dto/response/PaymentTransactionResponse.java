package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class PaymentTransactionResponse {
    private UUID id;
    private UUID paymentId;
}

