package com.shelton.e_commerce.repository;

import com.shelton.e_commerce.entity.Inventory;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface InventoryRepository extends JpaRepository<Inventory, UUID> { }
