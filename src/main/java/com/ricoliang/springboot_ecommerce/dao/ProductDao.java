package com.ricoliang.springboot_ecommerce.dao;

import com.ricoliang.springboot_ecommerce.model.Product;
import com.ricoliang.springboot_ecommerce.request.ProductRequest;

public interface ProductDao {
    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

}
