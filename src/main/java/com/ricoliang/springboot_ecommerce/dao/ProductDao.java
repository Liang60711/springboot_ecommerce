package com.ricoliang.springboot_ecommerce.dao;

import com.ricoliang.springboot_ecommerce.model.Product;

public interface ProductDao {

    Product getProductById(Integer productId);
}
