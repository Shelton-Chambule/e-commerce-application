package com.shelton.e_commerce.mapper;
import com.shelton.e_commerce.dto.request.UserRequest;
import com.shelton.e_commerce.dto.response.UserResponse;
import com.shelton.e_commerce.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shelton.e_commerce.entity.PurchaseOrder;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(target = "orderIds", source = "orders")
    UserResponse toResponse(User user);

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "name")
    @Mapping(target = "email")
    @Mapping(target = "password")
    @Mapping(target = "phone")
    User toEntity (UserRequest userRequest);

    default UUID map(PurchaseOrder order) {
        return order == null ? null : order.getId();
    }
}

