package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.model.Product;

public interface ProductService {

    Product getProductById(Integer productId);

    Product getProductByIdJpa(Integer productId);
}
