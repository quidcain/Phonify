package com.expertsoft.service;

import com.expertsoft.model.CartIndicator;
import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;

import java.util.List;


public interface OrderService {
    Order get(long id);
    void save(Order order);
    void delete(long id);
    List<Order> findAll();
    void addOrderItem(long id, long quantity);
    CartIndicator getCartIndicator();
    List<OrderItem> getOrderItems();
    void deleteOrderItem(long phoneId);
    void updateOrderItem(long phoneId, long newQuantity);
}