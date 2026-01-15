package com.emart.services.impl;

import com.emart.dtos.ProductDtoRequest;
import com.emart.dtos.ProductDtoResponse;
import com.emart.entities.Product;
import com.emart.repositories.ProductRepository;
import com.emart.services.ProductService;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

    @Override
    public List<ProductDtoResponse> getAllProducts() {
        return productRepository.findAll().stream()
                .map(product -> new ProductDtoResponse(product.getId(), product.getName(), product.getPrice()))
                .collect(Collectors.toList());
    }

    @Override
    public ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest) {
        Product product = new Product();
        product.setName(productDtoRequest.getName());
        product.setPrice(productDtoRequest.getPrice());
        
        Product savedProduct = productRepository.save(product);
        
        return new ProductDtoResponse(savedProduct.getId(), savedProduct.getName(), savedProduct.getPrice());
    }

    @Override
    public ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        
        product.setName(productDtoRequest.getName());
        product.setPrice(productDtoRequest.getPrice());
        
        Product updatedProduct = productRepository.save(product);
        
        return new ProductDtoResponse(updatedProduct.getId(), updatedProduct.getName(), updatedProduct.getPrice());
    }

    @Override
    public void deleteProduct(Long id) {
        Product product = productRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Product not found with id: " + id));
        productRepository.delete(product);
    }
}
