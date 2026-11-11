package com.shelton.e_commerce.repository;

import com.shelton.e_commerce.entity.InventoryMovement;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InventoryMovementRepository extends JpaRepository<InventoryMovement, UUID> { }
