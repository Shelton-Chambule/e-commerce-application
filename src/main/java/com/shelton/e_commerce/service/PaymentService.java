package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.PaymentResponse;
import com.shelton.e_commerce.entity.Payment;
import com.shelton.e_commerce.mapper.PaymentMapper;
import com.shelton.e_commerce.repository.PaymentRepository;
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
public class PaymentService {
    private final PaymentRepository repository;
    private final PaymentMapper mapper;

    public Page<PaymentResponse> findAll(Pageable pageable) {
        log.info("Listing Payment records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public PaymentResponse findById(UUID id) {
        log.info("Loading Payment with id={}", id);
        Payment entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Payment not found: " + id));
        return mapper.toResponse(entity);
    }
}

