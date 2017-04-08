package com.expertsoft.service;


import com.expertsoft.dao.OrderDao;
import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Order;
import com.expertsoft.model.OrderItem;
import com.expertsoft.model.Phone;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceTest {
    private Order order = new Order();

    @Mock
    private OrderDao orderDao;

    @Mock
    private PhoneDao phoneDao;

    @InjectMocks
    private OrderServiceImpl orderService;

    @Before
    public void init() {
        orderService = new OrderServiceImpl(orderDao, phoneDao, order);
    }

    @Test
    public void findAllTest() {
        List <Order> list = new ArrayList<>();
        when(orderDao.findAll()).thenReturn(list);
        orderService.findAll();
        verify(orderDao, times(1)).findAll();
    }

    @Test
    public void getTest() {
        Order order = new Order();
        when(orderDao.get(anyLong())).thenReturn(order);
        orderService.get(0);
        verify(orderDao, times(1)).get(anyLong());
    }

    @Test
    public void saveTest() {
        doNothing().when(orderDao).save(any(Order.class));
        orderService.save(new Order());
        verify(orderDao, times(1)).save(any(Order.class));
    }

    @Test
    public void deleteTest() {
        doNothing().when(orderDao).delete(anyLong());
        orderService.delete(0);
        verify(orderDao, times(1)).delete(anyLong());
    }

    @Test
    public void addOrderItemTest() {
        Phone phone = createPhone("iPhone");
        when(phoneDao.get(1)).thenReturn(phone);
        phone = createPhone("Motorola");
        when(phoneDao.get(2)).thenReturn(phone);
        orderService.addOrderItem(1, "1");
        assertEquals(1, orderService.getItemsQuantity());
        assertEquals("1", orderService.getSubtotal());
        orderService.addOrderItem(1, "2");
        assertEquals(3, orderService.getItemsQuantity());
        assertEquals("3", orderService.getSubtotal());

        orderService.addOrderItem(2, "3");
        assertEquals(6, orderService.getItemsQuantity());
        assertEquals("6", orderService.getSubtotal());
    }

    @Test
    public void getOrderItemsTest() {
        List<OrderItem> list = new ArrayList<>();
        order.setOrderItems(list);
        assertEquals(0, orderService.getOrderItems().size());
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        list.add(orderItem);
        assertEquals(1, orderService.getOrderItems().size());
    }

    @Test
    public void reduceOrderItemTest() {
        Phone phone = createPhone("iPhone");
        OrderItem orderItem = new OrderItem();
        orderItem.setOrder(order);
        orderItem.setPhone(phone);
        orderItem.setQuantity(3);
        List<OrderItem> list = new ArrayList<>();
        list.add(orderItem);
        order.setOrderItems(list);
        orderService.reduceOrderItem(1, 2);
        assertEquals(1L, orderItem.getQuantity());
        orderService.reduceOrderItem(1, 1);
        assertEquals(0, order.getOrderItems().size());
        orderService.reduceOrderItem(2, 2);
        assertEquals(0, order.getOrderItems().size());
    }

    private Phone createPhone(String model) {
        Phone phone = new Phone();
        phone.setId(1);
        phone.setModel(model);
        phone.setColor("black");
        phone.setDisplaySize(4);
        phone.setPrice(BigDecimal.ONE);
        return phone;
    }
}
