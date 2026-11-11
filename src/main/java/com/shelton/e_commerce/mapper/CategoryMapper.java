package com.shelton.e_commerce.mapper;
import com.shelton.e_commerce.dto.response.CategoryResponse;
import com.shelton.e_commerce.entity.Category;
import com.shelton.e_commerce.entity.Product;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import java.util.UUID;

@Mapper(componentModel = "spring")
public interface CategoryMapper {

    @Mapping(target = "productIds", source = "products")
    CategoryResponse toResponse(Category entity);

    default UUID map(Product product) {
        return product == null ? null : product.getId();
    }
}

