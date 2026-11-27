package com.ecommerce_microservices.product_service.dto.request;

import jakarta.validation.constraints.NotBlank;

public record CreateCategoryRequest(

    @NotBlank(message = "Name is required")
    String name,

    String description

) {}
