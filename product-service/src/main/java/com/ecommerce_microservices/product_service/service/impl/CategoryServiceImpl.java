package com.ecommerce_microservices.product_service.service.impl;

import com.ecommerce_microservices.product_service.exception.ErrorCode;
import com.ecommerce_microservices.product_service.exception.ProductServiceException;
import com.ecommerce_microservices.product_service.entity.Category;
import com.ecommerce_microservices.product_service.dto.request.CreateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.request.UpdateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.UpdateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.CategoryDetailResponse;
import com.ecommerce_microservices.product_service.service.CategoryService;
import com.ecommerce_microservices.product_service.repository.CategoryRepository;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.Optional;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "CATEGORY-SERVICE")
public class CategoryServiceImpl implements CategoryService {

    private final CategoryRepository categoryRepository;

    // @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public CreateCategoryResponse createCategory(CreateCategoryRequest request) {
        if(categoryRepository.existsByNameIgnoreCase(request.name())) {
            throw new ProductServiceException(ErrorCode.CATEGORY_EXISTED);
        }

        Category category = Category.builder()
            .name(request.name())
            .description(request.description())
            .build();

        categoryRepository.save(category);

        return CreateCategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .createdAt(category.getCreatedAt())
            .build();
    }

    @Override
    public List<CategoryDetailResponse> getAllCategories() {
        return categoryRepository.findAll()
            .stream()
            .map(category -> CategoryDetailResponse.builder()
                .id(category.getId())
                .name(category.getName())
                .description(category.getDescription())
                .createdAt(category.getCreatedAt())
                .build())
            .toList();
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public UpdateCategoryResponse updateCategory(String id, UpdateCategoryRequest request) {
        Category category = categoryRepository.findById(id)
            .orElseThrow(() -> new ProductServiceException(ErrorCode.CATEGORY_NOT_FOUND));

        Optional.ofNullable(request.name()).ifPresent(category::setName);
        Optional.ofNullable(request.description()).ifPresent(category::setDescription);

        categoryRepository.save(category);
        return UpdateCategoryResponse.builder()
            .id(category.getId())
            .name(category.getName())
            .description(category.getDescription())
            .createdAt(category.getCreatedAt())
            .build();
    }

    // @PreAuthorize("hasAuthority('ADMIN')")
    @Override
    public void deleteCategory(String id) {
        categoryRepository.findById(id)
            .ifPresent(categoryRepository::delete);
        log.info("Category deleted successfully");
    }

}
