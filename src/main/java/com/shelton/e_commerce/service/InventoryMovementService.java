package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.InventoryMovementResponse;
import com.shelton.e_commerce.entity.InventoryMovement;
import com.shelton.e_commerce.mapper.InventoryMovementMapper;
import com.shelton.e_commerce.repository.InventoryMovementRepository;
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
public class InventoryMovementService {
    private final InventoryMovementRepository repository;
    private final InventoryMovementMapper mapper;

    public Page<InventoryMovementResponse> findAll(Pageable pageable) {
        log.info("Listing InventoryMovement records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public InventoryMovementResponse findById(UUID id) {
        log.info("Loading InventoryMovement with id={}", id);
        InventoryMovement entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("InventoryMovement not found: " + id));
        return mapper.toResponse(entity);
    }
}

