package com.ricoliang.springboot_ecommerce.dao;

import com.ricoliang.springboot_ecommerce.dto.OrderQueryParams;
import com.ricoliang.springboot_ecommerce.model.Order;
import com.ricoliang.springboot_ecommerce.model.OrderItem;

import java.util.List;

public interface OrderDao {

    Integer countOrder(OrderQueryParams orderQueryParams);

    List<Order> getOrders(OrderQueryParams orderQueryParams);

    Order getOrderById(Integer orderId);

    List<OrderItem> getOrderItemsByOrderId(Integer orderId);

    Integer createOrder(Integer userId, Integer totalAmount);

    void createOrderItems(Integer orderId, List<OrderItem> orderItemList);

}
