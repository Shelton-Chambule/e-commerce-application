package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.OrderItemResponse;
import com.shelton.e_commerce.entity.OrderItem;
import com.shelton.e_commerce.mapper.OrderItemMapper;
import com.shelton.e_commerce.repository.OrderItemRepository;
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
public class OrderItemService {
    private final OrderItemRepository repository;
    private final OrderItemMapper mapper;

    public Page<OrderItemResponse> findAll(Pageable pageable) {
        log.info("Listing OrderItem records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public OrderItemResponse findById(UUID id) {
        log.info("Loading OrderItem with id={}", id);
        OrderItem entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("OrderItem not found: " + id));
        return mapper.toResponse(entity);
    }
}

