package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.ProductResponse;
import com.shelton.e_commerce.entity.Product;
import com.shelton.e_commerce.mapper.ProductMapper;
import com.shelton.e_commerce.repository.ProductRepository;
import com.shelton.e_commerce.exception.ResourceNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepository repository;
    private final ProductMapper mapper;

    public Page<ProductResponse> findAll(Pageable pageable) {
        log.info("Listing Product records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public ProductResponse findById(UUID id) {
        log.info("Loading Product with id={}", id);
        Product entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Product not found: " + id));
        return mapper.toResponse(entity);
    }
}

