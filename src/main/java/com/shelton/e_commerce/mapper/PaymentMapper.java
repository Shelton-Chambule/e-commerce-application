package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.PaymentResponse;
import com.shelton.e_commerce.entity.Payment;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentMapper {
    @Mapping(target = "orderId", source = "order.id")
    @Mapping(target = "transactionId", source = "transaction.id")
    PaymentResponse toResponse(Payment entity);
}

