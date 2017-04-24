package com.expertsoft.model;


import org.junit.Test;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotEquals;

public class CartTest {
    private Cart cart = new Cart();

    @Test
    public void equalsNull() {
        assertFalse(cart.equals(null));
    }

    @Test
    public void equalsAnotherClass() {
        assertFalse(cart.equals("cart"));
    }

    @Test
    public void equalsCart() {
        assertEquals(cart, cart);
        Cart anotherCart = new Cart();
        anotherCart.setId(-2L);
        assertNotEquals(cart, anotherCart);
    }

    @Test
    public void setterGetterTest() {
        cart.setId(-1L);
        assertEquals(new Long(-1L), cart.getId());

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
