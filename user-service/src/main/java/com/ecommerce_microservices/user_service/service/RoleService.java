package com.ecommerce_microservices.user_service.service;

import com.ecommerce_microservices.user_service.entity.Role;

public interface RoleService {

    Role createRole(String roleName);

}
