package com.ecommerce_microservices.product_service.dto.response;

import com.ecommerce_microservices.product_service.enums.ProductStatus;
import com.ecommerce_microservices.product_service.entity.ProductImage;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Builder;
import java.math.BigDecimal;
import java.time.Instant;
import java.util.List;

@Builder
@JsonInclude(JsonInclude.Include.NON_NULL)
public record ProductDetailResponse(

    String id,
    String name,
    String description,
    BigDecimal price,
    Integer quantity,
    List<ProductImage> images,
    ProductStatus status,
    Instant createdAt

) {}
