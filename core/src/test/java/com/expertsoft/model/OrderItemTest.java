package com.expertsoft.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.*;

/**
 * Created by stoat on 3/4/17.
 */
public class OrderItemTest {
    private OrderItem orderItem = new OrderItem();
    @Test
    public void equalsNull() {
        assertFalse(orderItem.equals(null));
    }

    @Test
    public void setterGetterTest() {
        Order order = new Order();
        orderItem.setOrder(order);
        assertEquals(order, orderItem.getOrder());

        Phone phone = new Phone("iPhone", "black", 4, BigDecimal.valueOf(800));;
        orderItem.setPhone(phone);
        assertEquals(phone, orderItem.getPhone());

        orderItem.setQuantity(4);
        assertEquals(4, orderItem.getQuantity());
    }
}
