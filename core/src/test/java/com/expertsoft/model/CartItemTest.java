package com.expertsoft.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CartItemTest {
    private CartItem cartItem = new CartItem();

    @Test
    public void setterGetterTest() {
        Phone phone = new Phone();
        cartItem.setPhone(phone);
        assertEquals(phone, cartItem.getPhone());

        cartItem.setQuantity(4);
        assertEquals(4, cartItem.getQuantity());

        cartItem = new CartItem(phone, 4);
        assertEquals(phone, cartItem.getPhone());
        assertEquals(4, cartItem.getQuantity());
    }
}
