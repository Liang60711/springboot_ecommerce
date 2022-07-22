package com.ricoliang.springboot_ecommerce.service;

import com.ricoliang.springboot_ecommerce.dto.OrderQueryParams;
import com.ricoliang.springboot_ecommerce.dto.request.CreateOrderRequest;
import com.ricoliang.springboot_ecommerce.model.Order;

import java.util.List;

public interface OrderService {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest);

}
