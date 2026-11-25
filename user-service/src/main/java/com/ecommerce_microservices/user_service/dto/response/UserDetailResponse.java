package com.ecommerce_microservices.user_service.dto.response;

import com.ecommerce_microservices.user_service.enums.UserStatus;
import com.ecommerce_microservices.user_service.enums.Gender;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDate;
import lombok.Builder;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Builder
@JsonInclude(NON_NULL)
public record UserDetailResponse(

    String email,
    String firstName,
    String lastName,
    String phone,
    String avatarKey,
    Gender gender,
    LocalDate birthDate,
    UserStatus userStatus

) {}
