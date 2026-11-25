package com.ecommerce_microservices.user_service.service.impl;

import com.ecommerce_microservices.user_service.dto.request.LoginRequest;
import com.ecommerce_microservices.user_service.dto.response.LoginResponse;
import com.ecommerce_microservices.user_service.service.AuthenticationService;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "AUTHENTICATION-SERVICE")
public class AuthenticationServiceImpl implements AuthenticationService {

    private final AuthenticationManager authenticationManager;

    @Override
    public LoginResponse login(LoginRequest request) {
        UsernamePasswordAuthenticationToken authenticationToken =
            new UsernamePasswordAuthenticationToken(request.email(), request.password());

        Authentication authenticate = authenticationManager.authenticate(authenticationToken);

        return LoginResponse.builder()
            .accessToken("mock-access-token")
            .refreshToken("mock-refresh-token")
            .build();
    }

}
