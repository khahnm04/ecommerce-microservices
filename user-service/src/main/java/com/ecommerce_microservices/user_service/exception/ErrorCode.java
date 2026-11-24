package com.ecommerce_microservices.user_service.exception;

import org.springframework.http.HttpStatus;
import lombok.RequiredArgsConstructor;
import lombok.Getter;

@Getter
@RequiredArgsConstructor
public enum ErrorCode {

    USER_ALREADY_EXISTS(400, "User already exists", HttpStatus.BAD_REQUEST);

    private final int code;
    private final String message;
    private final HttpStatus httpStatus;

}
