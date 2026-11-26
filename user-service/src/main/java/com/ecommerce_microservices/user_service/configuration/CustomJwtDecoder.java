package com.ecommerce_microservices.user_service.configuration;

import com.ecommerce_microservices.user_service.service.RedisTokenService;
import org.springframework.security.oauth2.jose.jws.MacAlgorithm;
import org.springframework.security.oauth2.jwt.NimbusJwtDecoder;
import org.springframework.security.oauth2.jwt.JwtException;
import org.springframework.security.oauth2.jwt.JwtDecoder;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import com.nimbusds.jwt.SignedJWT;
import jakarta.annotation.PostConstruct;
import javax.crypto.spec.SecretKeySpec;
import javax.crypto.SecretKey;
import lombok.RequiredArgsConstructor;
import java.text.ParseException;

@Component
@RequiredArgsConstructor
public class CustomJwtDecoder implements JwtDecoder {

    @Value("${jwt.secret-key}")
    private String secretKey;

    private NimbusJwtDecoder nimbusJwtDecoder = null;

    private final RedisTokenService redisTokenService;

    @PostConstruct
    public void init() {
        SecretKey key = new SecretKeySpec(secretKey.getBytes(), "HS512");
        nimbusJwtDecoder = NimbusJwtDecoder
            .withSecretKey(key)
            .macAlgorithm(MacAlgorithm.HS512)
            .build();
    }

    @Override
    public Jwt decode(String token) throws JwtException {
        try {
            SignedJWT signedJWT = SignedJWT.parse(token);
            String jwtId = signedJWT.getJWTClaimsSet().getJWTID();

            if(redisTokenService.existsByJwtId(jwtId)) {
                throw new JwtException("Token is expired");
            }
        } catch (ParseException e) {
            throw new RuntimeException(e);
        }
        return nimbusJwtDecoder.decode(token);
    }

}
