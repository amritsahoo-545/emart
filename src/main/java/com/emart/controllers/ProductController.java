package com.emart.controllers;

import com.emart.dtos.ProductDtoRequest;
import com.emart.dtos.ProductDtoResponse;
import com.emart.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@RequiredArgsConstructor
public class ProductController {

    private static final Logger logger = LogManager.getLogger(ProductController.class);

    private final ProductService productService;

    @GetMapping
    public ResponseEntity<List<ProductDtoResponse>> getProducts() {
        logger.info("Fetching all products");
        return ResponseEntity.ok().body(productService.getAllProducts());
    }
    
    @PostMapping
    public ResponseEntity<ProductDtoResponse> createProducts(@RequestBody ProductDtoRequest productDtoRequest){
        logger.info("Creating product: {}", productDtoRequest);
    	return ResponseEntity.ok().body(productService.createProduct(productDtoRequest));
    }

    @PutMapping("/{id}")
    public ResponseEntity<ProductDtoResponse> updateProduct(@PathVariable Long id, @RequestBody ProductDtoRequest productDtoRequest) {
        logger.info("Updating product with id: {}, data: {}", id, productDtoRequest);
        return ResponseEntity.ok().body(productService.updateProduct(id, productDtoRequest));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteProduct(@PathVariable Long id) {
        logger.info("Deleting product with id: {}", id);
        productService.deleteProduct(id);
        return ResponseEntity.ok().build();
    }
}
