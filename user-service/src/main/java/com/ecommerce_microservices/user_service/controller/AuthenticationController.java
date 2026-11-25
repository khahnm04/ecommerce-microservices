package com.ecommerce_microservices.user_service.controller;

import com.ecommerce_microservices.user_service.dto.response.ApiResponse;
import com.ecommerce_microservices.user_service.dto.request.LoginRequest;
import com.ecommerce_microservices.user_service.dto.response.LoginResponse;
import com.ecommerce_microservices.user_service.service.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.http.HttpStatus;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<LoginResponse> login(@RequestBody @Valid LoginRequest request) {
        var data = authenticationService.login(request);
        return ApiResponse.<LoginResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Login successful")
            .data(data)
            .build();
    }

}
