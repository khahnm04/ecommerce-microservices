package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.entity.RedisToken;

public interface RedisTokenService {

    void saveToken(RedisToken token);

    void deleteTokenByJwtId(String jwtId);

    boolean existsByJwtId(String jwtId);

}
