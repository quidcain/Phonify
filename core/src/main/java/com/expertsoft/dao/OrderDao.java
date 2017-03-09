package com.expertsoft.dao;

import com.expertsoft.model.Order;

import java.util.List;


public interface OrderDao {
    Order get(long id);
    void save(Order phone);
    void delete(long id);
    List<Order> findAll();
}
