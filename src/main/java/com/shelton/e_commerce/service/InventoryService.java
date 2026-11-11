package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.InventoryResponse;
import com.shelton.e_commerce.entity.Inventory;
import com.shelton.e_commerce.mapper.InventoryMapper;
import com.shelton.e_commerce.repository.InventoryRepository;
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
public class InventoryService {
    private final InventoryRepository repository;
    private final InventoryMapper mapper;

    public Page<InventoryResponse> findAll(Pageable pageable) {
        log.info("Listing Inventory records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public InventoryResponse findById(UUID id) {
        log.info("Loading Inventory with id={}", id);
        Inventory entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Inventory not found: " + id));
        return mapper.toResponse(entity);
    }
}

