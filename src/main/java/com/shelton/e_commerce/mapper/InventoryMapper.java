package com.shelton.e_commerce.mapper;
import com.shelton.e_commerce.dto.response.InventoryResponse;
import com.shelton.e_commerce.entity.Inventory;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface InventoryMapper {

    InventoryResponse toResponse(Inventory entity);
}

