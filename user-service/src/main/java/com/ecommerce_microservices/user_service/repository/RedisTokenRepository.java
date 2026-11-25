package com.ecommerce_microservices.user_service.repository;

import com.ecommerce_microservices.user_service.entity.RedisToken;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RedisTokenRepository extends CrudRepository<RedisToken, String> {
}
