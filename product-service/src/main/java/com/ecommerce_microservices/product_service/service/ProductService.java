package com.ecommerce_microservices.product_service.service;

import com.ecommerce_microservices.product_service.dto.request.CreateProductRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateProductResponse;
import com.ecommerce_microservices.product_service.dto.response.ProductDetailResponse;
import java.util.List;

public interface ProductService {

    CreateProductResponse createProduct(String sellerId, CreateProductRequest request);

    List<ProductDetailResponse> getAllProducts();

    ProductDetailResponse getProductById(String id);

    void deleteProduct(String id);

}
