package com.expertsoft.model;

import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;


public class OrderTest {
    private Order order = new Order();

    @Test
    public void equalsNull() {
        assertFalse(order.equals(null));
    }

    @Test
    public void setterGetterTest() {
        order.setId(123L);
        assertEquals(123L, order.getId());

        List<OrderItem> list = new ArrayList<>();
        list.add(new OrderItem());
        list.add(new OrderItem());
        order.setOrderItems(list);
        assertEquals(list, order.getOrderItems());

        order.setSubtotal(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, order.getSubtotal());

        order.setDeliveryPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, order.getDeliveryPrice());

        order.setTotalPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, order.getTotalPrice());

        order.setFirstName("John");
        assertEquals("John", order.getFirstName());

        order.setLastName("Doe");
        assertEquals("Doe", order.getLastName());

        order.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        assertEquals("1234 Main Street Anytown, USA 123456", order.getDeliveryAddress());

        order.setContactPhoneNo("1-800-354-0387");
        assertEquals("1-800-354-0387", order.getContactPhoneNo());
    }
}
