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

    /**
     * 新增商品
     */
    @PostMapping("/products")
    public ResponseEntity<Product> createProduct(@RequestBody @Valid ProductRequest productRequest) {
        Integer productId = productService.createProduct(productRequest);
        Product product = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.CREATED).body(product);
    }

    /**
     * 修改商品
     */
    @PutMapping("/products/{productId}")
    public ResponseEntity<Product> updateProduct(@PathVariable Integer productId,
                                                 @RequestBody @Valid ProductRequest productRequest) {

        // 檢查id是否存在
        Product product = productService.getProductById(productId);
        if (product == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }

        productService.updateProduct(productId, productRequest);
        Product updateProduct = productService.getProductById(productId);

        return ResponseEntity.status(HttpStatus.OK).body(updateProduct);
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
