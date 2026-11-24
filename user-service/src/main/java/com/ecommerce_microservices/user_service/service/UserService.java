package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.dto.request.CreateUserRequest;
import com.ecommerce_microservices.user_service.dto.response.CreateUserResponse;

public interface UserService {

    CreateUserResponse createUser(CreateUserRequest request);

}
