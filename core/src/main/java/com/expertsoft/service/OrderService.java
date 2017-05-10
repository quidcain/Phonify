package com.expertsoft.service;

import com.expertsoft.model.Cart;
import com.expertsoft.model.Order;

import java.util.List;
import java.util.Map;


public interface OrderService {
    Order get(long id);
    Order save(Cart cart);
    void delete(long id);
    List<Order> findAll();
    void changeStatuses(Map<Long, Order.Status> statuses);
}