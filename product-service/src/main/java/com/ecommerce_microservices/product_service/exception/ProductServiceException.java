package com.ecommerce_microservices.product_service.exception;

import lombok.Getter;

@Getter
public class ProductServiceException extends RuntimeException {

    private final ErrorCode errorCode;

    public ProductServiceException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.errorCode = errorCode;
    }

}
