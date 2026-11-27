package com.ecommerce_microservices.product_service.dto.response;

import com.ecommerce_microservices.product_service.enums.ProductStatus;
import com.ecommerce_microservices.product_service.entity.ProductImage;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
public record CreateProductResponse(

    String id,
    String name,
    String description,
    BigDecimal price,
    Integer quantity,
    List<ProductImage> images,
    ProductStatus status,
    Instant createdAt

) {}
