package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import java.util.Set;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class ProductResponse {
    private UUID id;
    private String name;
    private String description;
    private BigDecimal price;
    private Set<UUID> categoryIds;
}

