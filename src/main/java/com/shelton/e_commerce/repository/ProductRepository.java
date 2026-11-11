package com.shelton.e_commerce.repository;

import com.shelton.e_commerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ProductRepository extends JpaRepository<Product, UUID> { }
