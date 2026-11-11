package com.shelton.e_commerce.repository;

import com.shelton.e_commerce.entity.OrderItem;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OrderItemRepository extends JpaRepository<OrderItem, UUID> { }
