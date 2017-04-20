package com.expertsoft.model;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class CartIndicatorTest {
    private CartIndicator cartIndicator = new CartIndicator();

    @Test
    public void setterGetterTest() throws Throwable {
        cartIndicator.setItemsQuantity(4);
        assertEquals(4, cartIndicator.getItemsQuantity());
        cartIndicator.setSubtotal(BigDecimal.valueOf(4));
        assertEquals(BigDecimal.valueOf(4), cartIndicator.getSubtotal());
    }
}
