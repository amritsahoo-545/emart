package com.emart.services.impl;

import com.emart.dtos.ProductDtoRequest;
import com.emart.dtos.ProductDtoResponse;
import com.emart.entities.Product;
import com.emart.repositories.ProductRepository;
import com.emart.services.ProductService;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

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
}
