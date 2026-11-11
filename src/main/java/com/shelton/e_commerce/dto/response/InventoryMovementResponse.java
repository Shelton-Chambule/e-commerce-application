package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import com.shelton.e_commerce.entity.enums.InventoryStatus;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class InventoryMovementResponse {
    private UUID id;
    private InventoryStatus status;
}

