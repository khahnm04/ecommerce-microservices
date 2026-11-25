package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.dto.internal.TokenDetails;
import com.nimbusds.jose.JOSEException;
import com.nimbusds.jwt.SignedJWT;
import java.text.ParseException;
import java.util.Set;

public interface JwtService {

    String generateAccessToken(String userId, Set<String> roles);

    TokenDetails generateRefreshToken(String userId);

    SignedJWT validateToken(String token) throws ParseException, JOSEException;

}
