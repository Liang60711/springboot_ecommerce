package com.ricoliang.springboot_ecommerce.service.impl;

import com.ricoliang.springboot_ecommerce.dao.OrderDao;
import com.ricoliang.springboot_ecommerce.dao.ProductDao;
import com.ricoliang.springboot_ecommerce.dao.UserDao;
import com.ricoliang.springboot_ecommerce.dto.BuyItem;
import com.ricoliang.springboot_ecommerce.dto.OrderQueryParams;
import com.ricoliang.springboot_ecommerce.dto.request.CreateOrderRequest;
import com.ricoliang.springboot_ecommerce.model.Order;
import com.ricoliang.springboot_ecommerce.model.OrderItem;
import com.ricoliang.springboot_ecommerce.model.Product;
import com.ricoliang.springboot_ecommerce.model.User;
import com.ricoliang.springboot_ecommerce.service.OrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import java.util.ArrayList;
import java.util.List;

@Component
public class OrderServiceImpl implements OrderService {

    private final static Logger log = LoggerFactory.getLogger(OrderServiceImpl.class);
    @Autowired
    private OrderDao orderDao;

    @Autowired
    private ProductDao productDao;

    @Autowired
    private UserDao userDao;

    @Override
    public List<Order> getOrders(OrderQueryParams orderQueryParams) {
        List<Order> orderList = orderDao.getOrders(orderQueryParams);

        for(Order order : orderList) {
            List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(order.getOrderId());
            order.setOrderItemList(orderItemList);
        }
        return orderList;
    }

    @Override
    public Integer countOrder(OrderQueryParams orderQueryParams) {
        return orderDao.countOrder(orderQueryParams);
    }

    @Transactional
    @Override
    public Integer createOrder(Integer userId, CreateOrderRequest createOrderRequest) {

        // 檢查 user id
        User user = userDao.getUserById(userId);
        if (user == null) {
            log.warn("this userId {} does not exists.", userId);
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

        // 訂單建立
        int totalAmount = 0;
        List<OrderItem> orderItemList = new ArrayList<>();

        for (BuyItem buyItem : createOrderRequest.getBuyItemList()) {
            // 檢查庫存
            Product product = productDao.getProductById(buyItem.getProductId());

            if (product == null) {
                log.warn("this productId {} does not exists.", buyItem.getProductId());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            } else if (product.getStock() < buyItem.getQuantity()) {
                log.warn("productId {} stock number: {} ", buyItem.getProductId(), buyItem.getQuantity());
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
            }

            productDao.updateStock(product.getProductId(), product.getStock() - buyItem.getQuantity());

            // 計算訂單總金額
            int amount = buyItem.getQuantity() * product.getPrice();
            totalAmount += amount;

            // 建立 orderItem，並加入list
            OrderItem orderItem = new OrderItem();
            orderItem.setProductId(buyItem.getProductId());
            orderItem.setQuantity(buyItem.getQuantity());
            orderItem.setAmount(amount);

            orderItemList.add(orderItem);
        }

        Integer orderId = orderDao.createOrder(userId, totalAmount);

        orderDao.createOrderItems(orderId, orderItemList);

        return orderId;
    }

    @Override
    public Order getOrderById(Integer orderId) {

        Order order = orderDao.getOrderById(orderId);

        List<OrderItem> orderItemList = orderDao.getOrderItemsByOrderId(orderId);

        order.setOrderItemList(orderItemList);

        return order;
    }
}
