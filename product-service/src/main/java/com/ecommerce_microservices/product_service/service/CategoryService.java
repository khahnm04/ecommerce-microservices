package com.ecommerce_microservices.product_service.service;

import com.ecommerce_microservices.product_service.dto.request.CreateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.request.UpdateCategoryRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.UpdateCategoryResponse;
import com.ecommerce_microservices.product_service.dto.response.CategoryDetailResponse;
import java.util.List;

public interface CategoryService {

    CreateCategoryResponse createCategory(CreateCategoryRequest request);

    List<CategoryDetailResponse> getAllCategories();

    UpdateCategoryResponse updateCategory(String id, UpdateCategoryRequest request);

    void deleteCategory(String id);

}
