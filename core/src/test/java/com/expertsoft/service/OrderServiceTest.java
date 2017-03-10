package com.expertsoft.service;


import com.expertsoft.dao.OrderDao;
import com.expertsoft.dao.PhoneDao;
import com.expertsoft.model.Order;
import com.expertsoft.model.Phone;
import org.junit.Before;
import org.junit.Test;

import java.math.BigDecimal;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class OrderServiceTest {
    private OrderService orderService;
    private OrderDaoSpy orderDaoSpy;
    private PhoneDao phoneDao;
    private Order order;

    @Test
    public void orderDaoShouldNotBeNull() {
        assertNotNull(orderService);
    }

    @Before
    public void init() {
        order = new Order();
        orderDaoSpy = new OrderDaoSpy();
        phoneDao = mock(PhoneDao.class);
        orderService = new OrderServiceImpl(orderDaoSpy, phoneDao, order);
    }

    @Test
    public void findAllTest() {
        orderService.findAll();
        assertEquals(1, orderDaoSpy.getFindAllCallCount());
    }

    @Test
    public void getTest() {
        orderService.get(0);
        assertEquals(1, orderDaoSpy.getGetCallCount());
    }

    @Test
    public void saveTest() {
        orderService.save(order);
        assertEquals(1, orderDaoSpy.getSaveCallCount());
    }

    @Test
    public void deleteTest() {
        orderService.delete(0);
        assertEquals(1, orderDaoSpy.getDeleteCallCount());
    }

    @Test
    public void addOrderItemTest() {
        Phone phone = new Phone("iPhone", "black", 4, BigDecimal.ONE);
        when(phoneDao.get(1)).thenReturn(phone);
        orderService.addOrderItem(1, "1");
        assertEquals(1, orderService.getItemsQuantity());
        assertEquals("1", orderService.getSubtotal());
        orderService.addOrderItem(1, "2");
        assertEquals(3, orderService.getItemsQuantity());
        assertEquals("3", orderService.getSubtotal());
    }

    private class OrderDaoSpy implements OrderDao {
        private int getCallCount;
        private int saveCallCount;
        private int deleteCallCount;
        private int findAllCallCount;

        public int getGetCallCount() {
            return getCallCount;
        }

        public int getSaveCallCount() {
            return saveCallCount;
        }

        public int getDeleteCallCount() {
            return deleteCallCount;
        }

        public int getFindAllCallCount() {
            return findAllCallCount;
        }

        @Override
        public Order get(long id) {
            getCallCount++;
            return null;
        }

        @Override
        public void save(Order order) {
            saveCallCount++;
        }

        @Override
        public void delete(long id) {
            deleteCallCount++;
        }

        @Override
        public List<Order> findAll() {
            findAllCallCount++;
            return null;
        }
    }
}
