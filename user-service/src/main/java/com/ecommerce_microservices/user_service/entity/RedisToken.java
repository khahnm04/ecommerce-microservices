package com.ecommerce_microservices.user_service.entity;

import org.springframework.data.redis.core.index.Indexed;
import org.springframework.data.redis.core.TimeToLive;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.data.annotation.Id;
import java.util.concurrent.TimeUnit;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@RedisHash("redis_token")
public class RedisToken {

    @Id
    private String jwtId;

    @Indexed
    private String userId;

    @TimeToLive(unit = TimeUnit.SECONDS)
    private Long expiration;

}
