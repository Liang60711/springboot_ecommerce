package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.model.Product;
import com.ricoliang.springboot_ecommerce.request.ProductRequest;

public interface ProductService {

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    Product getProductByIdJpa(Integer productId);
}
