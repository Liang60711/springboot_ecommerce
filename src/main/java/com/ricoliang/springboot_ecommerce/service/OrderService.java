package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.dto.request.CreateOrderRequest;

public interface OrderService {

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);
}
