package com.ecommerce_microservices.product_service.service.impl;

import com.ecommerce_microservices.product_service.exception.ErrorCode;
import com.ecommerce_microservices.product_service.exception.ProductServiceException;
import com.ecommerce_microservices.product_service.entity.Category;
import com.ecommerce_microservices.product_service.entity.Product;
import com.ecommerce_microservices.product_service.dto.request.SearchRequest;
import com.ecommerce_microservices.product_service.dto.request.CreateProductRequest;
import com.ecommerce_microservices.product_service.dto.response.CreateProductResponse;
import com.ecommerce_microservices.product_service.dto.response.ProductDetailResponse;
import com.ecommerce_microservices.product_service.service.ProductService;
import com.ecommerce_microservices.product_service.repository.CategoryRepository;
import com.ecommerce_microservices.product_service.repository.ProductRepository;
import com.ecommerce_microservices.product_service.repository.specification.ProductSpecification;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.Authentication;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.stream.Collectors;
import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Slf4j(topic = "PRODUCT-SERVICE")
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final CategoryRepository categoryRepository;

    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    @Override
    public CreateProductResponse createProduct(String sellerId, CreateProductRequest request) {
        Category category = categoryRepository.findById(request.categoryId())
            .orElseThrow(() -> new ProductServiceException(ErrorCode.CATEGORY_NOT_FOUND));

        Product product = Product.builder()
            .sellerId(sellerId)
            .name(request.name())
            .description(request.description())
            .price(request.price())
            .quantity(request.quantity())
            .images(request.images())
            .status(request.status())
            .category(category)
            .build();

        productRepository.save(product);
        log.info("Product created successfully");

        return CreateProductResponse.builder()
            .id(product.getId())
            .name(product.getName())
            .description(product.getDescription())
            .price(product.getPrice())
            .quantity(product.getQuantity())
            .images(product.getImages())
            .status(product.getStatus())
            .createdAt(product.getCreatedAt())
            .build();
    }

    @Override
    public List<ProductDetailResponse> getAllProducts(SearchRequest request) {

        Specification<Product> specification = Specification.allOf(
            ProductSpecification.hasName(request.name()),
            ProductSpecification.hasPrice(request.minPrice(), request.maxPrice()),
            ProductSpecification.hasStatus(request.status()),
            ProductSpecification.inStock(request.inStock()),
            ProductSpecification.hasCategory(request.categoryId())
        );

        return productRepository.findAll(specification)
            .stream()
            .map(product -> ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .images(product.getImages())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .build())
            .toList();
    }

    @Override
    public ProductDetailResponse getProductById(String id) {
        return productRepository.findById(id)
            .map(product -> ProductDetailResponse.builder()
                .id(product.getId())
                .name(product.getName())
                .description(product.getDescription())
                .price(product.getPrice())
                .quantity(product.getQuantity())
                .images(product.getImages())
                .status(product.getStatus())
                .createdAt(product.getCreatedAt())
                .build())
            .orElseThrow(() -> new ProductServiceException(ErrorCode.PRODUCT_NOT_FOUND));
    }

    @PreAuthorize("hasAnyAuthority('SELLER','ADMIN')")
    @Override
    public void deleteProduct(String id) {
        Product product = productRepository.findById(id)
            .orElseThrow(() -> new ProductServiceException(ErrorCode.PRODUCT_NOT_FOUND));

        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication == null) {
            throw new ProductServiceException(ErrorCode.UNAUTHORIZED);
        }
        String userId = authentication.getName();

        if (!product.getSellerId().equals(userId)) {
            Set<String> authorities = authentication.getAuthorities()
                .stream().map(GrantedAuthority::getAuthority)
                .collect(Collectors.toSet());

            if(!authorities.contains("ADMIN")) {
                throw new ProductServiceException(ErrorCode.PRODUCT_ACCESS_DENIED);
            }
        }

        productRepository.delete(product);
        log.info("Product deleted successfully");
    }

}
