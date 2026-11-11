package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryRequest {
    private Integer availableQuantity;
    private Integer reservedQuantity;
}
