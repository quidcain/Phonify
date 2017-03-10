package com.expertsoft.service;

import com.expertsoft.model.Order;

import java.util.List;


public interface OrderService {
    Order get(long id);
    void save(Order order);
    void delete(long id);
    List<Order> findAll();
    void addOrderItem(long id, String quantity);
    long getItemsQuantity();
    String getSubtotal();
}