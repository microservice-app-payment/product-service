package com.vn.service;

import com.vn.model.ProductRequest;
import com.vn.model.ProductResponse;

import java.util.List;

public interface ProductService {
    long addProduct(ProductRequest productRequest);

    List<ProductResponse> getAllProduct();

    ProductResponse findById(Long id);
}
