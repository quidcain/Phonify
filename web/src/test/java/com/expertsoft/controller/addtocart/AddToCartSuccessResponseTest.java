package com.expertsoft.controller.addtocart;

import org.junit.Test;

import java.math.BigDecimal;

import static org.junit.Assert.assertEquals;


public class AddToCartSuccessResponseTest {
    private AddToCartSuccessResponse response = new AddToCartSuccessResponse();

    @Test
    public void setterGetterTest() throws Throwable {
        response.setItemsQuantity(4);
        assertEquals(4, response.getItemsQuantity());
        response.setSubtotal(BigDecimal.valueOf(4));
        assertEquals(BigDecimal.valueOf(4), response.getSubtotal());
    }
}
