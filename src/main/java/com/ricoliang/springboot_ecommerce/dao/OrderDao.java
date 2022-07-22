package com.ricoliang.springboot_ecommerce.dao;

import com.ricoliang.springboot_ecommerce.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);
}
