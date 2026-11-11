package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.PurchaseOrderResponse;
import com.shelton.e_commerce.entity.PurchaseOrder;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shelton.e_commerce.entity.OrderItem;
import com.shelton.e_commerce.entity.Payment;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface PurchaseOrderMapper {
    @Mapping(target = "userId", source = "user.id")
    @Mapping(target = "itemIds", source = "items")
    @Mapping(target = "paymentId", source = "payment.id")
    PurchaseOrderResponse toResponse(PurchaseOrder entity);

    default UUID map(OrderItem item) {
        return item == null ? null : item.getId();
    }

    default UUID map(Payment payment) {
        return payment == null ? null : payment.getId();
    }
}

