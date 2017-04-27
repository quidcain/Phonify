package com.expertsoft.model;


import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

public class CartItemTest {
    private CartItem cartItem = new CartItem();

    @Test
    public void equalsOrderItem() {
        assertEquals(cartItem, cartItem);
        CartItem anotherCartItem = new CartItem();
        anotherCartItem.setId(-2L);
        assertNotEquals(cartItem, anotherCartItem);
    }

    @Test
    public void setterGetterTest() {
        cartItem.setId(-1L);
        assertEquals(new Long(-1L), cartItem.getId());

        Phone phone = new Phone();
        cartItem.setPhone(phone);
        assertEquals(phone, cartItem.getPhone());

        Cart cart = new Cart();
        cartItem.setCart(cart);
        assertEquals(cart, cartItem.getCart());

        cartItem.setQuantity(4);
        assertEquals(4, cartItem.getQuantity());

        cartItem = new CartItem(phone, cart, 4);
        assertEquals(phone, cartItem.getPhone());
        assertEquals(cart, cartItem.getCart());
        assertEquals(4, cartItem.getQuantity());
    }
}
