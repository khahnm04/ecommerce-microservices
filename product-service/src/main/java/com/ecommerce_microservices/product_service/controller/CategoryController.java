package com.ecommerce_microservices.product_service.controller;

import com.ecommerce_microservices.product_service.dto.response.ApiResponse;
import com.ecommerce_microservices.product_service.dto.request.CreateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.request.UpdateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.UpdateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.CategoryDetailResponse;
import com.ecommerce_microservices.product_service.service.CategoryService;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/categories")
public class CategoryController {

    private final CategoryService categoryService;

    @PostMapping
    ApiResponse<CreateCategoryResponse> createCategory(
        @RequestBody @Valid CreateCategoryRequest request
    ) {
        var data = categoryService.createCategory(request);
        return ApiResponse.<CreateCategoryResponse>builder()
            .code(HttpStatus.CREATED.value())
            .message("Category created successfully")
            .data(data)
            .build();
    }

    @GetMapping
    ApiResponse<List<CategoryDetailResponse>> getCategories() {
        var data = categoryService.getAllCategories();
        return ApiResponse.<List<CategoryDetailResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("Categories retrieved successfully")
            .data(data)
            .build();
    }

    @PutMapping("/{id}")
    ApiResponse<UpdateCategoryResponse> updateCategory(
        @PathVariable String id,
        @RequestBody @Valid UpdateCategoryRequest request
    ) {
        var data = categoryService.updateCategory(id, request);
        return ApiResponse.<UpdateCategoryResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Category updated successfully")
            .data(data)
            .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteCategory(
        @PathVariable String id
    ) {
        categoryService.deleteCategory(id);
        return ApiResponse.<Void>builder()
            .code(HttpStatus.NO_CONTENT.value())
            .message("Category deleted successfully")
            .build();
    }
    
}
