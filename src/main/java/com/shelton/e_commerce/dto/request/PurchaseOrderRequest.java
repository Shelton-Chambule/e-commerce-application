package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import java.util.List;
import java.time.LocalDateTime;
import java.math.BigDecimal;
import com.shelton.e_commerce.entity.enums.OrderStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class PurchaseOrderRequest {
    private UUID userId;
    private List<UUID> itemIds;
}
