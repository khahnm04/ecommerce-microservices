package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.dto.request.CreateUserRequest;
import com.ecommerce_microservices.user_service.dto.response.CreateUserResponse;
import com.ecommerce_microservices.user_service.dto.response.UserDetailResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

    UserDetailResponse myInfo(String userId);

}
