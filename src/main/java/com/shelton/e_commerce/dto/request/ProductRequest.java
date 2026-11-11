package com.shelton.e_commerce.dto.request;

import java.util.UUID;
import java.util.Set;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class ProductRequest {
    private String name;
    private String description;
    private BigDecimal price;
    private Set<UUID> categoryIds;
}
