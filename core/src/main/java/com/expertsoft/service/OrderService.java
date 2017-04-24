package com.expertsoft.service;

import com.expertsoft.model.Cart;
import com.expertsoft.model.Order;

import java.util.List;


public interface OrderService {
    Order get(long id);
    void save(Cart cart);
    void delete(long id);
    List<Order> findAll();
}