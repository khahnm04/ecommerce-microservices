package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.dto.request.LoginRequest;
import com.ecommerce_microservices.user_service.dto.response.LoginResponse;
import com.nimbusds.jose.JOSEException;
import java.text.ParseException;

public interface AuthenticationService {

    LoginResponse login(LoginRequest request);

    LoginResponse refreshToken(String refreshToken);

    void logout(String refreshToken) throws ParseException, JOSEException;

}
