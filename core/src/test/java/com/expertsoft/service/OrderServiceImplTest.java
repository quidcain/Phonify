package com.expertsoft.service;


import com.expertsoft.dao.OrderDao;
import com.expertsoft.model.Cart;
import com.expertsoft.model.CartItem;
import com.expertsoft.model.Order;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;


@RunWith(MockitoJUnitRunner.class)
public class OrderServiceImplTest {

    @Mock
    private OrderDao orderDao;

    private OrderServiceImpl orderService;


    @Before
    public void init() {
        orderService = new OrderServiceImpl(orderDao);
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
        doAnswer(invocationOnMock -> {
            Order order = (Order)invocationOnMock.getArguments()[0];
            order.setId(1);
            return order;
        }).when(orderDao).save(any(Order.class));
        Cart cart = new Cart();
        cart.setCartItems(new ArrayList<>());
        cart.getCartItems().add(new CartItem());
        Order order = orderService.save(cart);
        assertEquals(Long.valueOf(1), order.getId());
        verify(orderDao, times(1)).save(any(Order.class));
    }

    @Test
    public void deleteTest() {
        doNothing().when(orderDao).delete(anyLong());
        orderService.delete(0);
        verify(orderDao, times(1)).delete(anyLong());
    }
}
