package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import com.shelton.e_commerce.entity.enums.InventoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class InventoryMovementRequest {
    private InventoryStatus status;
}
