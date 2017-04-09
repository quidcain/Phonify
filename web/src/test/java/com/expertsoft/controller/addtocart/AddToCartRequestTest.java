package com.expertsoft.controller.addtocart;

import org.junit.Test;

import static org.junit.Assert.*;


public class AddToCartRequestTest {
    private AddToCartRequest request = new AddToCartRequest();

    @Test
    public void setterGetterTest() throws Throwable {
        request.setId(4);
        assertEquals(4, request.getId());
        request.setQuantity(4);
        assertEquals(4, request.getQuantity());
    }
}
