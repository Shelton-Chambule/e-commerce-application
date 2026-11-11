package com.shelton.e_commerce.entity;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import java.util.UUID;

@Entity
@Table(name = "inventories")
@Getter @Setter @NoArgsConstructor
public class Inventory {


    @Id @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "id", nullable = false, updatable = false)
    private UUID id;
    @Column(name = "available_quantity", nullable = false)
    private Integer availableQuantity;
    @Column(name = "reserved_quantity", nullable = false)
    private Integer reservedQuantity;
}
