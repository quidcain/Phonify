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
import java.util.NoSuchElementException;

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
    public void addOrderItem(long id, long quantity) {
        boolean itemAlreadyPresents = false;
        Phone phone = null;
        for (OrderItem item : order.getOrderItems()) {
            phone = item.getPhone();
            if (phone.getId() == id) {
                item.setQuantity(item.getQuantity() + quantity);
                itemAlreadyPresents = true;
                break;
            }
        }
        if (!itemAlreadyPresents) {
            phone = phoneDao.get(id);
            OrderItem item = new OrderItem(phone, order, quantity);
            order.getOrderItems().add(item);
        }
        order.setSubtotal(order.getSubtotal().add(
                phone.getPrice().multiply(BigDecimal.valueOf(quantity))));
        itemsQuantity += quantity;
    }

    @Override
    public long getItemsQuantity() {
        return itemsQuantity;
    }

    @Override
    public BigDecimal getSubtotal() {
        return order.getSubtotal();
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return order.getOrderItems();
    }

    @Override
    public void reduceOrderItem(long phoneId, long quantity) {
        int itemIndex = getItemIndex(phoneId);
        OrderItem item = order.getOrderItems().get(itemIndex);
        if(item.getQuantity() < quantity)
            throw new ItemsQuantityUnderflow();
        item.setQuantity(item.getQuantity() - quantity);
        itemsQuantity -= quantity;
        order.setSubtotal(order.getSubtotal().subtract(
                item.getPhone().getPrice().multiply(BigDecimal.valueOf(quantity))));
        if (item.getQuantity() < 1)
            order.getOrderItems().remove(itemIndex);
    }

    @Override
    public void updateOrderItem(long phoneId, long newQuantity) {
        int itemIndex = getItemIndex(phoneId);
        OrderItem item = order.getOrderItems().get(itemIndex);
        long quantity = newQuantity - item.getQuantity();
        item.setQuantity(newQuantity);
        itemsQuantity += quantity;
        order.setSubtotal(order.getSubtotal().add(
                item.getPhone().getPrice().multiply(BigDecimal.valueOf(quantity))));
    }

    private int getItemIndex(long phoneId) {
        List<OrderItem> orderItems = order.getOrderItems();
        for (int i = 0, j = orderItems.size(); i < j; i++) {
            OrderItem item = orderItems.get(i);
            if (item.getPhone().getId() == phoneId) {
                return i;
            }
        }
        throw new NoSuchElementException();
    }
}
