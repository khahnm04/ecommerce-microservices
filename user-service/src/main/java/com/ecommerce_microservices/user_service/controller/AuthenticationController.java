package com.ecommerce_microservices.user_service.controller;

import com.ecommerce_microservices.user_service.dto.response.ApiResponse;
import com.ecommerce_microservices.user_service.dto.request.LoginRequest;
import com.ecommerce_microservices.user_service.dto.response.LoginResponse;
import com.ecommerce_microservices.user_service.service.AuthenticationService;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.http.HttpStatus;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.Cookie;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/v1/auth")
public class AuthenticationController {

    private final AuthenticationService authenticationService;

    @PostMapping("/login")
    ApiResponse<LoginResponse> login(
        @RequestBody @Valid LoginRequest request,
        HttpServletResponse response
    ) {
        var data = authenticationService.login(request);

        Cookie cookie = new Cookie("refresh_token", data.refreshToken());
        cookie.setHttpOnly(true);
        cookie.setSecure(false);
        cookie.setPath("/");
        cookie.setMaxAge(14 * 24 * 60 * 60);
        response.addCookie(cookie);

        LoginResponse responseData = LoginResponse.builder()
            .accessToken(data.accessToken())
            .refreshToken(null)
            .roles(data.roles())
            .build();

        return ApiResponse.<LoginResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Login successful")
            .data(responseData)
            .build();
    }

    @PostMapping("/refresh-token")
    ApiResponse<LoginResponse> refreshToken(
        @CookieValue("refresh_token") String refreshToken
    ) {
        var data = authenticationService.refreshToken(refreshToken);

        LoginResponse responseData = LoginResponse.builder()
            .accessToken(data.accessToken())
            .refreshToken(null)
            .roles(data.roles())
            .build();

        return ApiResponse.<LoginResponse>builder()
            .code(HttpStatus.OK.value())
            .message("Token refreshed successfully")
            .data(responseData)
            .build();
    }

}
