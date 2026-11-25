package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.exception.ErrorCode;
import com.ecommerce_microservices.user_service.exception.UserServiceException;
import com.ecommerce_microservices.user_service.repository.UserRepository;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import org.jspecify.annotations.NonNull;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CustomUserDetailService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(@NonNull String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
            .orElseThrow(() -> new UserServiceException(ErrorCode.USER_NOT_FOUND));
    }

}
