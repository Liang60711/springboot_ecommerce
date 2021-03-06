package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.dto.ProductQueryParams;
import com.ricoliang.springboot_ecommerce.dto.request.ProductRequest;
import com.ricoliang.springboot_ecommerce.model.Product;

import java.util.List;

public interface ProductService {

    Integer countProduct(ProductQueryParams productQueryParams);

    List<Product> getProducts(ProductQueryParams productQueryParams);

    Product getProductById(Integer productId);

    Integer createProduct(ProductRequest productRequest);

    void updateProduct(Integer productId, ProductRequest productRequest);

    void deleteProductById(Integer productId);

    Product getProductByIdJpa(Integer productId);
}
