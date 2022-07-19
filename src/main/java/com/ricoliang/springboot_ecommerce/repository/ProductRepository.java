package com.ricoliang.springboot_ecommerce.repository;

import com.ricoliang.springboot_ecommerce.model.Product;
import org.springframework.data.repository.CrudRepository;

public interface ProductRepository extends CrudRepository<Product, Integer> {

}
