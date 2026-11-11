package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.PaymentTransactionResponse;
import com.shelton.e_commerce.entity.PaymentTransaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PaymentTransactionMapper {
    @Mapping(target = "paymentId", source = "payment.id")
    PaymentTransactionResponse toResponse(PaymentTransaction entity);
}

