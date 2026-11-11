package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.OrderItemResponse;
import com.shelton.e_commerce.entity.OrderItem;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface OrderItemMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "productId", source = "product.id")
    OrderItemResponse toResponse(OrderItem entity);
}

