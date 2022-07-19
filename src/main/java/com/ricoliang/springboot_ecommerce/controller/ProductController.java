package com.ricoliang.springboot_ecommerce.controller;

import com.ricoliang.springboot_ecommerce.request.ProductRequest;
import com.ricoliang.springboot_ecommerce.model.Product;
import com.ricoliang.springboot_ecommerce.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * JDBC
     * 查詢商品
     */
    @GetMapping("/products/{productId}")
    public ResponseEntity<Product> getProduct(@PathVariable Integer productId) {

        Product product = productService.getProductById(productId);

        if (product != null ) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * JPA
     */
    @GetMapping("/jpa/products/{productId}")
    public ResponseEntity<Product> getProductJpa(@PathVariable Integer productId) {

        Product product = productService.getProductByIdJpa(productId);

        if (product != null ) {
            return ResponseEntity.status(HttpStatus.OK).body(product);
        }else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }
}
