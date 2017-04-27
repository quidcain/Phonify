package com.expertsoft.model;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class CartTest {
    private Cart cart = new Cart();

    @Test
    public void setterGetterTest() {
        List<CartItem> list = new ArrayList<>();
        list.add(new CartItem());
        list.add(new CartItem());
        cart.setCartItems(list);
        assertEquals(list, cart.getCartItems());

        cart.setSubtotal(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, cart.getSubtotal());

        cart.setDeliveryPrice(BigDecimal.ONE);
        assertEquals(BigDecimal.ONE, cart.getDeliveryPrice());

        assertEquals(BigDecimal.valueOf(2), cart.getTotalPrice());

        cart.setFirstName("John");
        assertEquals("John", cart.getFirstName());

        cart.setLastName("Doe");
        assertEquals("Doe", cart.getLastName());

        cart.setDeliveryAddress("1234 Main Street Anytown, USA 123456");
        assertEquals("1234 Main Street Anytown, USA 123456", cart.getDeliveryAddress());

        cart.setContactPhoneNo("1-800-354-0387");
        assertEquals("1-800-354-0387", cart.getContactPhoneNo());

        cart.setAdditionalInfo("Lorem ipsum");
        assertEquals("Lorem ipsum", cart.getAdditionalInfo());

        CartIndicator cartIndicator = new CartIndicator();
        cart.setCartIndicator(cartIndicator);
        assertEquals(cartIndicator, cart.getCartIndicator());
    }
}
