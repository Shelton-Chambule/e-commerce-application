package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.InventoryMovementResponse;
import com.shelton.e_commerce.entity.InventoryMovement;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMovementMapper {
    InventoryMovementResponse toResponse(InventoryMovement entity);
}

