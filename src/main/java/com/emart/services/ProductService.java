package com.emart.services;

import java.util.List;

import com.emart.dtos.ProductDtoRequest;
import com.emart.dtos.ProductDtoResponse;

public interface ProductService {
    List<ProductDtoResponse> getAllProducts();
    ProductDtoResponse createProduct(ProductDtoRequest productDtoRequest);
    ProductDtoResponse updateProduct(Long id, ProductDtoRequest productDtoRequest);
    void deleteProduct(Long id);
}
