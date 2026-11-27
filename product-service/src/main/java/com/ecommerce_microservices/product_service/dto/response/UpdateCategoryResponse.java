package com.ecommerce_microservices.product_service.dto.response;

import lombok.Builder;
import java.time.Instant;

@Builder
public record UpdateCategoryResponse(

    String id,
    String name,
    String description,
    Instant createdAt

) {}
