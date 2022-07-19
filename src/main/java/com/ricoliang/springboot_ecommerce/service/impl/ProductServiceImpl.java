package com.ricoliang.springboot_ecommerce.service.impl;

import com.ricoliang.springboot_ecommerce.dao.ProductDao;
import com.ricoliang.springboot_ecommerce.model.Product;
import com.ricoliang.springboot_ecommerce.repository.ProductRepository;
import com.ricoliang.springboot_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductDao productDao;

    @Autowired
    private ProductRepository productRepository;

    @Override
    public Product getProductById(Integer productId) {
        return productDao.getProductById(productId);
    }

    @Override
    public Product getProductByIdJpa(Integer productId) {
        Product product = productRepository.findById(productId).orElse(null);
        return product;
    }
}
