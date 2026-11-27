package com.ecommerce_microservices.product_service.controller;

import com.ecommerce_microservices.product_service.dto.request.SearchRequest;
import com.ecommerce_microservices.product_service.dto.request.CreateProductRequest;
import com.ecommerce_microservices.product_service.dto.response.ApiResponse;
import com.ecommerce_microservices.product_service.dto.response.PageResponse;
import com.ecommerce_microservices.product_service.dto.response.CreateProductResponse;
import com.ecommerce_microservices.product_service.dto.response.ProductDetailResponse;
import com.ecommerce_microservices.product_service.service.ProductService;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import jakarta.validation.Valid;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/products")
public class ProductController {

    private final ProductService productService;

    @PostMapping
    ApiResponse<CreateProductResponse> createProduct(
        @AuthenticationPrincipal Jwt jwt,
        @RequestBody @Valid CreateProductRequest request
    ) {
        var data = productService.createProduct(jwt.getSubject(), request);
        return ApiResponse.<CreateProductResponse>builder()
            .code(HttpStatus.CREATED.value())
            .message("Product created successfully")
            .data(data)
            .build();
    }

    @GetMapping
    ApiResponse<PageResponse<ProductDetailResponse>> getProducts(
        @RequestParam(required = false, defaultValue = "1") int page,
        @RequestParam(required = false, defaultValue = "10") int size,
        SearchRequest request
    ) {
        var data = productService.getAllProducts(page, size, request);
        return ApiResponse.<PageResponse<ProductDetailResponse>>builder()
            .code(HttpStatus.OK.value())
            .message("Products retrieved successfully")
            .data(data)
            .build();
    }

    @GetMapping("/{id}")
    ApiResponse<ProductDetailResponse> getProductById(
        @PathVariable String id
    ) {
        var data = productService.getProductById(id);
        return ApiResponse.<ProductDetailResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Product retrieved successfully")
            .data(data)
            .build();
    }

    @DeleteMapping("/{id}")
    ApiResponse<Void> deleteProductById(
        @PathVariable String id
    ) {
        productService.deleteProduct(id);
        return ApiResponse.<Void>builder()
            .code(HttpStatus.NO_CONTENT.value())
            .message("Product deleted successfully")
            .build();
    }
    
}
