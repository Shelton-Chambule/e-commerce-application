package com.shelton.e_commerce.dto.response;
import java.util.UUID;
import java.util.Set;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter @NoArgsConstructor
public class CategoryResponse {

    private UUID id;
    private String name;
    private Set<UUID> productIds;
}

