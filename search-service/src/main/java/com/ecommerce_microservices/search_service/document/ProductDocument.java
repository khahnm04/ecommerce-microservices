package com.ecommerce_microservices.search_service.document;

import lombok.*;
import java.io.Serializable;
import java.time.Instant;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class ProductDocument implements Serializable {

    private String id;
    private String name;
    private String description;
    private Double price;
    private String categoryId;
    private String categoryName;
    private String thumbnail;
    private String status;
    private Boolean inStock;
    private Instant createdAt;

}
