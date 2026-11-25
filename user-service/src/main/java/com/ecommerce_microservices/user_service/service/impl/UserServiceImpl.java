package com.ecommerce_microservices.user_service.service.impl;

import com.ecommerce_microservices.user_service.dto.response.UserDetailResponse;
import com.ecommerce_microservices.user_service.exception.ErrorCode;
import com.ecommerce_microservices.user_service.exception.UserServiceException;
import com.ecommerce_microservices.user_service.enums.RoleType;
import com.ecommerce_microservices.user_service.entity.User;
import com.ecommerce_microservices.user_service.entity.Role;
import com.ecommerce_microservices.user_service.dto.request.CreateUserRequest;
import com.ecommerce_microservices.user_service.dto.response.CreateUserResponse;
import com.ecommerce_microservices.user_service.mapper.UserMapper;
import com.ecommerce_microservices.user_service.service.RoleService;
import com.ecommerce_microservices.user_service.service.UserService;
import com.ecommerce_microservices.user_service.repository.UserRepository;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "USER-SERVICE")
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserMapper userMapper;
    private final RoleService roleService;
    private final UserRepository userRepository;

    @Override
    public CreateUserResponse createUser(CreateUserRequest request) {
        User user = userMapper.toUser(request);
        user.setPassword(passwordEncoder.encode(request.password()));

        Role role = roleService.createRole(RoleType.CUSTOMER.name());
        user.addRole(role);

        try {
            userRepository.save(user);
        } catch (DataIntegrityViolationException exception) {
            log.error("User already exists");
            throw new UserServiceException(ErrorCode.USER_ALREADY_EXISTS);
        }
        return userMapper.toCreateUserResponse(user);
    }

    @Override
    public UserDetailResponse myInfo(String userId) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new UserServiceException(ErrorCode.USER_NOT_FOUND));

        return userMapper.toUserDetailResponse(user);
    }

}
