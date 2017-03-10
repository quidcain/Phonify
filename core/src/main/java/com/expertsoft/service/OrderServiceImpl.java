package com.expertsoft.service;

import com.expertsoft.dao.OrderDao;
import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    private OrderDao orderDao;
    private PhoneDao phoneDao;
    private Order order;
    private long itemsQuantity;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, PhoneDao phoneDao, Order order) {
        this.orderDao = orderDao;
        this.phoneDao = phoneDao;
        this.order = order;
        itemsQuantity = 0;
    }

    @Override
    public Order get(long id) {
        return orderDao.get(id);
    }

    @Override
    public void save(Order order) {
        orderDao.save(order);
    }

    @Override
    public void delete(long id) {
        orderDao.delete(id);
    }

    @Override
    public List<Order> findAll() {
        return orderDao.findAll();
    }

    @Override
    public void addOrderItem(long id, String quantity) {
        Phone phone = phoneDao.get(id);
        OrderItem item = new OrderItem(phone, order, Long.parseLong(quantity));
        order.getOrderItems().add(item);
        order.setSubtotal(order.getSubtotal().add(
                phone.getPrice().multiply(BigDecimal.valueOf(Long.parseLong(quantity)))));
        itemsQuantity += item.getQuantity();
    }

    @Override
    public long getItemsQuantity() {
        return itemsQuantity;
    }

    @Override
    public String getSubtotal() {
        return order.getSubtotal().toString();
    }
}
