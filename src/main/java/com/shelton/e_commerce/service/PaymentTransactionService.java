package com.shelton.e_commerce.service;

import com.shelton.e_commerce.dto.response.PaymentTransactionResponse;
import com.shelton.e_commerce.entity.PaymentTransaction;
import com.shelton.e_commerce.mapper.PaymentTransactionMapper;
import com.shelton.e_commerce.repository.PaymentTransactionRepository;
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
public class PaymentTransactionService {
    private final PaymentTransactionRepository repository;
    private final PaymentTransactionMapper mapper;

    public Page<PaymentTransactionResponse> findAll(Pageable pageable) {
        log.info("Listing PaymentTransaction records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public PaymentTransactionResponse findById(UUID id) {
        log.info("Loading PaymentTransaction with id={}", id);
        PaymentTransaction entity = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("PaymentTransaction not found: " + id));
        return mapper.toResponse(entity);
    }
}

