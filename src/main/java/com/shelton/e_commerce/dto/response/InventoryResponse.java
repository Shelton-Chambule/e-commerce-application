package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InventoryResponse {
    private UUID id;
    private Integer availableQuantity;
    private Integer reservedQuantity;
}

