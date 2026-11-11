package com.shelton.e_commerce.service;
import com.shelton.e_commerce.dto.request.CategoryRequest;
import com.shelton.e_commerce.dto.response.CategoryResponse;
import com.shelton.e_commerce.entity.Category;
import com.shelton.e_commerce.mapper.CategoryMapper;
import com.shelton.e_commerce.repository.CategoryRepository;
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
public class CategoryService {

    private final CategoryRepository repository;
    private final CategoryMapper mapper;


    public Page<CategoryResponse> findAll(Pageable pageable) {
        log.info("Listing Category records: page={}, size={}", pageable.getPageNumber(), pageable.getPageSize());
        return repository.findAll(pageable).map(mapper::toResponse);
    }

    public CategoryResponse findById(UUID id) {
        log.info("Loading Category with id={}", id);
        Category category = repository.findById(id).orElseThrow(() ->
                new ResourceNotFoundException("Category not found: " + id));
        return mapper.toResponse(category);
    }
}

