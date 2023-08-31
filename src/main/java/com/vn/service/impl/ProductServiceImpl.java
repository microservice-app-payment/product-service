package com.vn.service.impl;

import com.vn.entity.Product;
import com.vn.exception.ProductServiceCustomException;
import com.vn.model.ProductRequest;
import com.vn.model.ProductResponse;
import com.vn.repository.ProductRepository;
import com.vn.service.ProductService;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Log4j2
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductRepository productRepository;
    @Override
    public long addProduct(ProductRequest productRequest) {
        log.info("Adding Product..");
        Product product = Product
                .builder()
                .productName(productRequest.getProductName())
                .price(productRequest.getPrice())
                .quantity(productRequest.getQuantity())
                .build();
        productRepository.save(product);
        log.info("Product created");
        return product.getProductId();
    }

    @Override
    public List<ProductResponse> getAllProduct() {

        List<ProductResponse> list = productRepository.findAll()
                .stream()
                .map(product -> ProductResponse
                        .builder()
                            .productName(product.getProductName())
                            .productId(product.getProductId())
                            .quantity(product.getQuantity())
                            .price(product.getPrice())
                        .build())
                .collect(Collectors.toList());

        return list;
    }

    @Override
    public ProductResponse findById(Long id) {
        log.info("Get the product for ProductID {}",id);
        Product product = productRepository
                .findById(id)
                .orElseThrow(() -> new ProductServiceCustomException("Product with given id not found","PRODUCT_NOT_FOUND"));
        ProductResponse productResponse = new ProductResponse();

        BeanUtils.copyProperties(product,productResponse);

        return productResponse;
    }

    @Override
    public void reduceQuantity(Long productId, Long quantity) {
        log.info("Reduce Quantity {} for Id: {}",quantity,productId);
        Product product = productRepository.findById(productId)
                .orElseThrow(() -> new ProductServiceCustomException(""+
                    "Product with given Id not found","PRODUCT_NOT_FOUND"));
        if (product.getQuantity() < quantity){
            throw new ProductServiceCustomException(
                "Product does not have sufficient Quantity",
                "INSUFFICIENT_QUANTITY"
            );
        }

        product.setQuantity(product.getQuantity() - quantity);

        productRepository.save(product);

        log.info("Product Quantity updated Successfully");
    }
}
