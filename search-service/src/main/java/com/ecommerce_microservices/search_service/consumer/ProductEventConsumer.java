package com.ecommerce_microservices.search_service.consumer;

import com.ecommerce_microservices.event.ProductCreatedEvent;
import com.ecommerce_microservices.event.ProductDeletedEvent;
import com.ecommerce_microservices.search_service.document.ProductDocument;
import com.ecommerce_microservices.search_service.service.ProductDocumentService;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.kafka.annotation.KafkaHandler;
import org.springframework.stereotype.Component;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Component
@RequiredArgsConstructor
@Slf4j(topic = "PRODUCT-EVENT-CONSUMER")
@KafkaListener(topics = "product-events", groupId = "search-service-consumer")
public class ProductEventConsumer {

    private final ProductDocumentService productDocumentService;

    @KafkaHandler
    public void consumeProductCreatedEvent(@Payload ProductCreatedEvent event) {
        log.info("Consumed ProductCreatedEvent: {}", event.getId());
        productDocumentService.saveProductDocument(convertToDocument(event));
        log.info("Indexed product successfully: {}", event.getId());
    }

    @KafkaHandler
    public void consumeProductDeletedEvent(@Payload ProductDeletedEvent event) {
        log.info("Consumed ProductDeletedEvent: {}", event.getId());
        productDocumentService.deleteProductDocument(event.getId());
        log.info("Deleted product successfully: {}", event.getId());
    }

    private ProductDocument convertToDocument(ProductCreatedEvent event) {
        return ProductDocument.builder()
            .id(event.getId())
            .name(event.getName())
            .description(event.getDescription())
            .price(event.getPrice())
            .categoryId(event.getCategoryId())
            .categoryName(event.getCategoryName())
            .thumbnail(event.getThumbnail())
            .status(event.getStatus())
            .inStock(event.getInStock())
            .createdAt(event.getCreatedAt())
            .build();
    }

}
