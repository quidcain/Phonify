package com.expertsoft.model;

import org.junit.Test;


import static org.junit.Assert.*;


public class OrderItemTest {
    private OrderItem orderItem = new OrderItem();

    @Test
    public void equalsNull() {
        assertFalse(orderItem.equals(null));
    }

    @Test
    public void equalsAnotherClass() {
        assertFalse(orderItem.equals("orderItem"));
    }

    @Test
    public void equalsOrderItem() {
        assertEquals(orderItem, orderItem);
        OrderItem anotherOrderItem = new OrderItem();
        anotherOrderItem.setId(-2L);
        assertNotEquals(orderItem, anotherOrderItem);
    }

    @Test
    public void setterGetterTest() {
        orderItem.setId(-1L);
        assertEquals(new Long(-1L), orderItem.getId());

        Phone phone = new Phone();
        orderItem.setPhone(phone);
        assertEquals(phone, orderItem.getPhone());

        Order order = new Order();
        orderItem.setOrder(order);
        assertEquals(order, orderItem.getOrder());

        orderItem.setQuantity(4);
        assertEquals(4, orderItem.getQuantity());

        orderItem = new OrderItem(phone, order, 4);
        assertEquals(phone, orderItem.getPhone());
        assertEquals(order, orderItem.getOrder());
        assertEquals(4, orderItem.getQuantity());
    }
}
