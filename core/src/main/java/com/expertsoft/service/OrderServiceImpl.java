package com.expertsoft.service;

import com.expertsoft.dao.OrderDao;
import com.expertsoft.model.Cart;
import com.expertsoft.model.CartItem;
import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class OrderServiceImpl implements OrderService {
    private OrderDao orderDao;

    @Autowired
    public OrderServiceImpl(OrderDao orderDao) {
        this.orderDao = orderDao;
    }

    @Override
    public Order get(long id) {
        return orderDao.get(id);
    }

    @Override
    public Order save(Cart cart) {
        Order order = new Order();
        order.setOrderItems(new ArrayList<>());
        order.setSubtotal(cart.getSubtotal());
        order.setDeliveryPrice(cart.getDeliveryPrice());
        order.setFirstName(cart.getFirstName());
        order.setLastName(cart.getLastName());
        order.setDeliveryAddress(cart.getDeliveryAddress());
        order.setContactPhoneNo(cart.getContactPhoneNo());
        order.setAdditionalInfo(cart.getAdditionalInfo());
        order.setStatus(Order.Status.AWAITING);
        for(CartItem cartItem : cart.getCartItems()) {
            OrderItem orderItem = new OrderItem();
            orderItem.setPhone(cartItem.getPhone());
            orderItem.setOrder(order);
            orderItem.setQuantity(cartItem.getQuantity());
            order.getOrderItems().add(orderItem);
        }
        orderDao.save(order);
        return order;
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
    public void changeStatuses(Map<Long, Order.Status> statuses) {
        List<Order> orders = orderDao.findAll();
        for (Order order : orders) {
            Long id = order.getId();
            orderDao.delete(id);
            order.setStatus(statuses.get(id));
            orderDao.save(order);
        }
    }
}
