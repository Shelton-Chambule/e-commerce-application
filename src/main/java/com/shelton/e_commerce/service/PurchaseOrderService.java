package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.PurchaseOrderResponse;
import com.shelton.e_commerce.entity.PurchaseOrder;
import com.shelton.e_commerce.mapper.PurchaseOrderMapper;
import com.shelton.e_commerce.repository.PurchaseOrderRepository;
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
public class PurchaseOrderService {
    private final PurchaseOrderRepository repository;
    private final PurchaseOrderMapper mapper;

    public Page<PurchaseOrderResponse> findAll(Pageable pageable) {
        log.info("Listing PurchaseOrder records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public PurchaseOrderResponse findById(UUID id) {
        log.info("Loading PurchaseOrder with id={}", id);
        PurchaseOrder entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("PurchaseOrder not found: " + id));
        return mapper.toResponse(entity);
    }
}

