package com.ecommerce_microservices.product_service.entity;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ProductImage {

    private String url;
    private Boolean isPrimary;
    private Integer displayOrder;

}
