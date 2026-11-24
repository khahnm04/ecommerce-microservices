package com.ecommerce_microservices.user_service.mapper;

import com.ecommerce_microservices.user_service.dto.request.CreateUserRequest;
import com.ecommerce_microservices.user_service.dto.response.CreateUserResponse;
import com.ecommerce_microservices.user_service.entity.User;
import org.mapstruct.NullValuePropertyMappingStrategy;
import org.mapstruct.Mapping;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    @Mapping(target = "password", ignore = true)
    User toUser(CreateUserRequest request);

    CreateUserResponse toCreateUserResponse(User user);

}
