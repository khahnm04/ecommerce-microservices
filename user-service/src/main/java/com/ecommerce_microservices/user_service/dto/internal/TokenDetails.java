package com.ecommerce_microservices.user_service.dto.internal;

import lombok.Builder;

@Builder
public record TokenDetails(

    String value,
    String jwtId,
    long ttlSeconds

) {}
