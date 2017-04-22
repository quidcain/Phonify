package com.expertsoft.service;

import com.expertsoft.dao.OrderDao;
import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.CartIndicator;
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
    private CartIndicator cartIndicator;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao, PhoneDao phoneDao, Order order) {
        this.orderDao = orderDao;
        this.phoneDao = phoneDao;
        this.order = order;
        cartIndicator = new CartIndicator();
        cartIndicator.setItemsQuantity(0);
        cartIndicator.setSubtotal(BigDecimal.ZERO);
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
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() + quantity);
        cartIndicator.setSubtotal(order.getSubtotal());
    }

    @Override
    public CartIndicator getCartIndicator() {
        return cartIndicator;
    }

    @Override
    public List<OrderItem> getOrderItems() {
        return order.getOrderItems();
    }

    @Override
    public void deleteOrderItem(long phoneId) {
        int itemIndex = getItemIndex(phoneId);
        List<OrderItem> orderItems = order.getOrderItems();
        OrderItem item = orderItems.get(itemIndex);
        order.setSubtotal(order.getSubtotal().subtract(
            item.getPhone().getPrice().multiply(BigDecimal.valueOf(item.getQuantity()))));
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() - item.getQuantity());
        cartIndicator.setSubtotal(order.getSubtotal());
        orderItems.remove(itemIndex);
    }

    @Override
    public void updateOrderItem(long phoneId, long newQuantity) {
        int itemIndex = getItemIndex(phoneId);
        OrderItem item = order.getOrderItems().get(itemIndex);
        long quantityDifference = newQuantity - item.getQuantity();
        if (quantityDifference == 0)
            return;
        item.setQuantity(newQuantity);
        order.setSubtotal(order.getSubtotal().add(
                item.getPhone().getPrice().multiply(BigDecimal.valueOf(quantityDifference))));
        cartIndicator.setItemsQuantity(cartIndicator.getItemsQuantity() + quantityDifference);
        cartIndicator.setSubtotal(order.getSubtotal());
    }

    @Override
    public Order getOrder() {
        return order;
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
