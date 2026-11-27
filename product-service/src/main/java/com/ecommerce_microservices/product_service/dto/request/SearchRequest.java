package com.ecommerce_microservices.product_service.dto.request;

import com.ecommerce_microservices.product_service.enums.ProductStatus;
import java.math.BigDecimal;

public record SearchRequest(

    String categoryId,
    String name,
    BigDecimal minPrice,
    BigDecimal maxPrice,
    ProductStatus status,
    Boolean inStock

) {}
