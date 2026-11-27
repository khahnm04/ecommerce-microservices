package com.ecommerce_microservices.product_service.dto.request;

public record UpdateCategoryRequest(

    String name,
    String description

) {}
