package com.shelton.e_commerce.mapper;

import com.shelton.e_commerce.dto.response.ProductResponse;
import com.shelton.e_commerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import com.shelton.e_commerce.entity.Category;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface ProductMapper {
    @Mapping(target = "categoryIds", source = "categories")
    ProductResponse toResponse(Product entity);

    default UUID map(Category category) {
        return category == null ? null : category.getId();
    }
}

