package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.dto.request.LoginRequest;
import com.ecommerce_microservices.user_service.dto.response.LoginResponse;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

}
