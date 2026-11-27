package com.ecommerce_microservices.product_service.repository;

import com.ecommerce_microservices.product_service.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CategoryRepository extends JpaRepository<Category, String> {

    boolean existsByNameIgnoreCase(String name);

}
