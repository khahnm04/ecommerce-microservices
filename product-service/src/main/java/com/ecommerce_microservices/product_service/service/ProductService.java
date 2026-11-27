package com.ecommerce_microservices.product_service.service;

import com.ecommerce_microservices.product_service.dto.response.PageResponse;
import com.ecommerce_microservices.product_service.dto.request.SearchRequest;
import com.ecommerce_microservices.product_service.dto.request.CreateProductRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateProductResponse;
import com.ecommerce_microservices.product_service.dto.response.ProductDetailResponse;

public interface ProductService {

    CreateProductResponse createProduct(String sellerId, CreateProductRequest request);

    PageResponse<ProductDetailResponse> getAllProducts(int page, int size, SearchRequest request);

    ProductDetailResponse getProductById(String id);

    void deleteProduct(String id);

}
