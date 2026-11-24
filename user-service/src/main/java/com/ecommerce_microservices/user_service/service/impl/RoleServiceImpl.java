package com.ecommerce_microservices.user_service.service.impl;

import com.ecommerce_microservices.user_service.entity.Role;
import com.ecommerce_microservices.user_service.service.RoleService;
import com.ecommerce_microservices.user_service.repository.RoleRepository;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role createRole(String roleName) {
        return roleRepository.findByName(roleName)
            .orElseGet(() -> roleRepository.save(
                Role.builder()
                    .name(roleName)
                    .build()
            ));
    }

}
