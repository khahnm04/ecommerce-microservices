package com.ecommerce_microservices.search_service.service;

import com.ecommerce_microservices.search_service.document.ProductDocument;

public interface ProductDocumentService {

    void saveProductDocument(ProductDocument document);

    void deleteProductDocument(String id);

}
